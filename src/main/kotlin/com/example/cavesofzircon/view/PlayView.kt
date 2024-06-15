package com.example.cavesofzircon.view

import com.example.cavesofzircon.GameConfig
import com.example.cavesofzircon.GameConfig.LOG_AREA_HEIGHT
import com.example.cavesofzircon.GameConfig.SIDEBAR_WIDTH
import com.example.cavesofzircon.GameConfig.WINDOW_WIDTH
import com.example.cavesofzircon.builders.GameTileRepository
import com.example.cavesofzircon.world.Game
import org.hexworks.cobalt.databinding.api.extension.toProperty
import org.hexworks.zircon.api.ComponentDecorations.box
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ColorTheme
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.game.ProjectionMode
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.view.base.BaseView
import org.hexworks.zircon.internal.game.impl.GameAreaComponentRenderer

/**
 * @author nick
 * @since 2024/06/15
 */
class PlayView(
  private val grid: TileGrid,
  private val game: Game = Game.create(),
  theme: ColorTheme = GameConfig.THEME
) : BaseView(grid, theme) {
  init {
    val sidebar = Components.panel()
      .withSize(SIDEBAR_WIDTH, GameConfig.WINDOW_HEIGHT)
      .withDecorations(box())
      .build()

    val logArea = Components.logArea()
      .withDecorations(box(title = "Log"))
      .withSize(WINDOW_WIDTH - SIDEBAR_WIDTH, LOG_AREA_HEIGHT)
      .withAlignmentWithin(screen, ComponentAlignment.BOTTOM_RIGHT)
      .build()

    val gameComponent = Components.panel()
      .withSize(game.world.visibleSize.to2DSize())
      .withComponentRenderer(
        GameAreaComponentRenderer(
          gameArea = game.world,
          projectionMode = ProjectionMode.TOP_DOWN.toProperty(),
          fillerTile = GameTileRepository.FLOOR
        )
      )
      .withAlignmentWithin(screen, ComponentAlignment.TOP_RIGHT)
      .build()

    screen.addComponents(sidebar, logArea, gameComponent)
  }
}