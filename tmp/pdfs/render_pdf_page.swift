import AppKit
import Foundation
import ImageIO
import PDFKit
import UniformTypeIdentifiers

guard CommandLine.arguments.count >= 4 else {
    FileHandle.standardError.write(Data("usage: render_pdf_page.swift PDF PAGE OUTPUT.png\n".utf8))
    exit(2)
}

let input = CommandLine.arguments[1]
let pageNumber = Int(CommandLine.arguments[2]) ?? 1
let output = CommandLine.arguments[3]
guard let document = PDFDocument(url: URL(fileURLWithPath: input)),
      pageNumber >= 1,
      pageNumber <= document.pageCount,
      let page = document.page(at: pageNumber - 1) else {
    FileHandle.standardError.write(Data("could not open requested page\n".utf8))
    exit(1)
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
) else { exit(1) }
context.setFillColor(NSColor.white.cgColor)
context.fill(CGRect(x: 0, y: 0, width: width, height: height))
context.saveGState()
context.scaleBy(x: scale, y: scale)
page.draw(with: .mediaBox, to: context)
context.restoreGState()
guard let image = context.makeImage(),
      let destination = CGImageDestinationCreateWithURL(
        URL(fileURLWithPath: output) as CFURL,
        UTType.png.identifier as CFString,
        1,
        nil
      ) else { exit(1) }
CGImageDestinationAddImage(destination, image, nil)
guard CGImageDestinationFinalize(destination) else { exit(1) }
