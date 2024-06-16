package com.example.cavesofzircon.attributes

import org.hexworks.amethyst.api.base.BaseAttribute
import org.hexworks.zircon.api.data.Tile

/**
 * @author nick
 * @since 2024/06/16
 */
data class EntityTile(val tile: Tile = Tile.empty()) : BaseAttribute()
