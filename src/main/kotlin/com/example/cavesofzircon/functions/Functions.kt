package com.example.cavesofzircon.functions

import com.example.cavesofzircon.events.GameLogEvent
import com.example.cavesofzircon.world.GameContext
import org.hexworks.zircon.internal.Zircon

fun logGameEvent(text: String, emitter: Any) {
  Zircon.eventBus.publish(GameLogEvent(text, emitter))
}

fun initializePlayerView(context: GameContext) {
  val world = context.world
  val screen = context.screen
  val game = context.game

  world.initializePlayerView(screen, game)
}
