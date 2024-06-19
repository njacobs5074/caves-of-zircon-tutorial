package com.example.cavesofzircon.events

import org.hexworks.cobalt.events.api.Event

/**
 * @author nick
 * @since 2024/06/19
 */
data class GameLogEvent(
  val text: String,
  override val emitter: Any
) : Event
