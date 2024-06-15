package com.example.cavesofzircon

import com.example.cavesofzircon.view.StartView
import org.hexworks.zircon.api.CP437TilesetResources
import org.hexworks.zircon.api.SwingApplications
import org.hexworks.zircon.api.application.AppConfig

fun main() {

    val grid = SwingApplications.startTileGrid(
        AppConfig.newBuilder()
            .withDefaultTileset(CP437TilesetResources.rogueYun16x16())
            .build()
    )

    StartView(grid).dock()
}
