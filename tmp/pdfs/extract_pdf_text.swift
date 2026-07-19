import Foundation
import PDFKit

guard CommandLine.arguments.count >= 2 else {
    FileHandle.standardError.write(Data("usage: extract_pdf_text.swift PDF...\n".utf8))
    exit(2)
}

for path in CommandLine.arguments.dropFirst() {
    let url = URL(fileURLWithPath: path)
    print("===== FILE: \(path) =====")
    guard let document = PDFDocument(url: url) else {
        print("[UNREADABLE PDF]")
        continue
    }
    print("[PAGES: \(document.pageCount)]")
    for index in 0..<document.pageCount {
        print("----- PAGE \(index + 1) -----")
        if let text = document.page(at: index)?.string, !text.isEmpty {
            print(text)
        } else {
            print("[NO EXTRACTABLE TEXT]")
        }
    }
}
