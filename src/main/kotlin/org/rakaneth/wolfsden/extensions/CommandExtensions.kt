package org.rakaneth.wolfsden.extensions

import org.hexworks.amethyst.api.Command
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.entity.EntityType
import org.rakaneth.wolfsden.world.GameContext

inline fun <reified T : Command<out EntityType, GameContext>> Command<out EntityType, GameContext>.responseWhenCommandIs(
    noinline fn: (T) -> Response
): Response {
    return responseWhenCommandIs(T::class, fn)
}