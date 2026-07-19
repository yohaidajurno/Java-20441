import AppKit
import Foundation
import PDFKit
import Vision

guard CommandLine.arguments.count >= 2 else {
    FileHandle.standardError.write(Data("usage: ocr_pdf.swift PDF [first-page] [last-page]\n".utf8))
    exit(2)
}

let path = CommandLine.arguments[1]
let firstRequested = CommandLine.arguments.count >= 3 ? Int(CommandLine.arguments[2]) ?? 1 : 1
let lastRequested = CommandLine.arguments.count >= 4 ? Int(CommandLine.arguments[3]) : nil

guard let document = PDFDocument(url: URL(fileURLWithPath: path)) else {
    FileHandle.standardError.write(Data("could not open PDF: \(path)\n".utf8))
    exit(1)
}

let first = max(1, firstRequested)
let last = min(document.pageCount, lastRequested ?? document.pageCount)
print("===== FILE: \(path) [PAGES \(first)-\(last) OF \(document.pageCount)] =====")

for pageNumber in first...last {
    autoreleasepool {
        guard let page = document.page(at: pageNumber - 1) else { return }
        print("----- PAGE \(pageNumber) -----")
        if let embedded = page.string?.trimmingCharacters(in: .whitespacesAndNewlines), embedded.count >= 40 {
            print(embedded)
            return
        }

        let bounds = page.bounds(for: .mediaBox)
        let targetWidth: CGFloat = 2200
        let scale = targetWidth / max(bounds.width, 1)
        let width = Int(bounds.width * scale)
        let height = Int(bounds.height * scale)
        guard let context = CGContext(
            data: nil,
            width: width,
            height: height,
            bitsPerComponent: 8,
            bytesPerRow: 0,
            space: CGColorSpaceCreateDeviceRGB(),
            bitmapInfo: CGImageAlphaInfo.premultipliedLast.rawValue
        ) else {
            print("[RENDER FAILED]")
            return
        }
        context.setFillColor(NSColor.white.cgColor)
        context.fill(CGRect(x: 0, y: 0, width: width, height: height))
        context.saveGState()
        context.scaleBy(x: scale, y: scale)
        page.draw(with: .mediaBox, to: context)
        context.restoreGState()
        guard let image = context.makeImage() else {
            print("[RENDER FAILED]")
            return
        }

        let request = VNRecognizeTextRequest()
        request.recognitionLevel = .accurate
        request.usesLanguageCorrection = true
        let supported = (try? request.supportedRecognitionLanguages()) ?? []
        let preferred = ["he-IL", "en-US"].filter { supported.contains($0) }
        if pageNumber == first {
            FileHandle.standardError.write(Data("Vision OCR languages: \(supported.joined(separator: ","))\n".utf8))
        }
        if !preferred.isEmpty {
            request.recognitionLanguages = preferred
        }
        do {
            try VNImageRequestHandler(cgImage: image, options: [:]).perform([request])
            let observations = request.results ?? []
            let lines = observations.compactMap { $0.topCandidates(1).first?.string }
            print(lines.isEmpty ? "[NO TEXT RECOGNIZED]" : lines.joined(separator: "\n"))
        } catch {
            print("[OCR FAILED: \(error)]")
        }
    }
}
