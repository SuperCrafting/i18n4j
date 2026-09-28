package dev.kurai.i18n4j.adventure;

import static net.kyori.adventure.text.minimessage.tag.resolver.Placeholder.component;

import com.google.common.collect.Lists;
import dev.kurai.i18n4j.Translation;
import dev.kurai.i18n4j.TranslationArgument;
import dev.kurai.i18n4j.TranslationParser;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

public class MiniMessageTranslationParser implements TranslationParser<Component> {

  private final MiniMessage miniMessage;

  public MiniMessageTranslationParser(final MiniMessage miniMessage) {
    this.miniMessage = miniMessage;
  }

  @Override
  public Component parse(final Translation translation, final TranslationArgument... arguments) {
    final var resolvers = Lists.<TagResolver>newArrayList();

    for (final TranslationArgument argument : arguments) {
      resolvers.add(component(argument.key(), ComponentUnboxer.unbox(argument.value())));
    }

    return miniMessage.deserialize(translation.content(), resolvers.toArray(TagResolver[]::new));
  }
}
