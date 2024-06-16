package com.example.cavesofzircon.world

import com.example.cavesofzircon.attributes.types.Player
import com.example.cavesofzircon.extensions.GameEntity
import org.hexworks.amethyst.api.Context
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.UIEvent

/**
 * @author nick
 * @since 2024/06/16
 */
data class GameContext(
  val world: World,
  val screen: Screen,
  val uiEvent: UIEvent,
  val player: GameEntity<Player>
) : Context
