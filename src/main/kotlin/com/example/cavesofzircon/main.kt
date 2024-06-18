package com.example.cavesofzircon

import com.example.cavesofzircon.view.StartView
import org.hexworks.zircon.api.SwingApplications

fun main() {
  val grid = SwingApplications.startTileGrid(GameConfig.buildAppConfig())
  StartView(grid).dock()
}
