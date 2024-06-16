package com.example.cavesofzircon.attributes

import org.hexworks.amethyst.api.base.BaseAttribute
import org.hexworks.cobalt.databinding.api.extension.toProperty
import org.hexworks.zircon.api.data.Position3D

/**
 * @author nick
 * @since 2024/06/16
 */
class EntityPosition(
  initialPosition: Position3D = Position3D.unknown()
) : BaseAttribute() {

  private val positionProperty = initialPosition.toProperty()
  var position: Position3D by positionProperty.asDelegate()
}