package com.example.cavesofzircon.builders

import com.example.cavesofzircon.blocks.GameBlock

/**
 * @author nick
 * @since 2024/06/15
 */
object GameBlockFactory {

  fun floor() = GameBlock(GameTileRepository.FLOOR)
  fun wall() = GameBlock.createWith(EntityFactory.newWall())

}