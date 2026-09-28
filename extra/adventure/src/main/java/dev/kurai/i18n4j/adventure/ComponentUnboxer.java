package dev.kurai.i18n4j.adventure;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;

final class ComponentUnboxer {

  private ComponentUnboxer() {}

  public static Component unbox(final Object object) {
    if (object instanceof ComponentLike componentLike) {
      return componentLike.asComponent();
    }
    return Component.text(String.valueOf(object));
  }

}
