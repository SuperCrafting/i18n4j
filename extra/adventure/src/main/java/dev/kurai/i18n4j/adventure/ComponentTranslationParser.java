package dev.kurai.i18n4j.adventure;

import dev.kurai.i18n4j.Translation;
import dev.kurai.i18n4j.TranslationArgument;
import dev.kurai.i18n4j.TranslationParser;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.serializer.ComponentDecoder;

record ComponentTranslationParser<T>(TranslationParser<T> parser, ComponentDecoder<T, Component> decoder) implements TranslationParser<Component> {

  @Override
  public Component parse(final Translation translation, final TranslationArgument... arguments) {
    Component result = decoder.deserialize(parser.parse(translation)); // arguments not passed

    for (TranslationArgument argument : arguments) {
      result = result.replaceText(builder -> {
        builder.matchLiteral(argument.key())
                .replacement(ComponentUnboxer.unbox(argument.value()));
      });
    }

    return result;
  }
}
