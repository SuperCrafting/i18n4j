package dev.kurai.i18n4j.adventure;

import dev.kurai.i18n4j.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.ComponentDecoder;

public class RawComponentTranslationParser<T> implements TranslationParser<Component> {

  private final TranslationParser<T> parser;
  private final ComponentDecoder<T, Component> decoder;

  public RawComponentTranslationParser(final TranslationParser<T> parser, final ComponentDecoder<T, Component> decoder) {
    this.parser = parser;
    this.decoder = decoder;
  }

  @Override
  public Component parse(final Translation translation, final TranslationArgument... arguments) {
    return decoder.deserialize(parser.parse(translation, arguments));
  }
}
