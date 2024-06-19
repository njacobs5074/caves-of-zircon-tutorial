package com.example.cavesofzircon.attributes

import com.example.cavesofzircon.GameConfig
import org.hexworks.amethyst.api.base.BaseAttribute

/**
 * @author nick
 * @since 2024/06/19
 */
data class FungusSpread(
  var spreadCount: Int = 0,
  val maximumSpread: Int = GameConfig.MAXIMUM_FUNGUS_SPREAD
) : BaseAttribute()
