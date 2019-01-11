package org.rakaneth.wolfsden.extensions

import org.hexworks.zircon.api.data.impl.Position3D
import squidpony.squidmath.Coord

fun Coord.toPos3D(z: Int) = Position3D.create(this.x, this.y, z)
fun Position3D.toCoord(): Coord {
    val pos = this.to2DPosition()
    return Coord.get(pos.x, pos.y)
}