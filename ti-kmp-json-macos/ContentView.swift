//
//  ContentView.swift
//  ti-kmp-json-macos
//
//  Created by Tõnis Tiganik on 26/6/21.
//

import SwiftUI
import KmpJsonKit

struct ContentView: View {
    let kmpJson = KmpJsonKit()

    var body: some View {
        VStack {
            Text("Hello, world!")
                .padding()
        }.frame(width: 300, height: 200)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
