package com.example.cavesofzircon.builders

import com.example.cavesofzircon.builders.GameColors.FLOOR_BACKGROUND
import com.example.cavesofzircon.builders.GameColors.WALL_BACKGROUND
import com.example.cavesofzircon.builders.GameColors.WALL_FOREGROUND
import org.hexworks.zircon.api.data.CharacterTile
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.graphics.Symbols

/**
 * @author nick
 * @since 2024/06/15
 */
object GameTileRepository {

  val EMPTY: CharacterTile = Tile.empty()

  val FLOOR: CharacterTile = Tile.newBuilder()
    .withCharacter(Symbols.INTERPUNCT)
    .withForegroundColor(FLOOR_BACKGROUND)
    .withBackgroundColor(FLOOR_BACKGROUND)
    .buildCharacterTile()

  val WALL: CharacterTile = Tile.newBuilder()
    .withCharacter('#')
    .withForegroundColor(WALL_FOREGROUND)
    .withBackgroundColor(WALL_BACKGROUND)
    .buildCharacterTile()
}