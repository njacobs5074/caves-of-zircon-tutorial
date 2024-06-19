package com.example.cavesofzircon.builders

import com.example.cavesofzircon.builders.GameColors.ACCENT_COLOR
import com.example.cavesofzircon.builders.GameColors.FLOOR_BACKGROUND
import com.example.cavesofzircon.builders.GameColors.FUNGUS_COLOR
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

  val PLAYER = Tile.newBuilder()
    .withCharacter('@')
    .withBackgroundColor(FLOOR_BACKGROUND)
    .withForegroundColor(ACCENT_COLOR)
    .buildCharacterTile()

  val FUNGUS = Tile.newBuilder()
    .withCharacter('f')
    .withBackgroundColor(FLOOR_BACKGROUND)
    .withForegroundColor(FUNGUS_COLOR)
    .buildCharacterTile()
}