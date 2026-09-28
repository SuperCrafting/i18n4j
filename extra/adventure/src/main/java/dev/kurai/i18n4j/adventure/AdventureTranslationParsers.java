package dev.kurai.i18n4j.adventure;

import dev.kurai.i18n4j.TranslationParser;
import dev.kurai.i18n4j.TranslationParsers;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public final class AdventureTranslationParsers {

  public static final TranslationParser<Component> RAW_COMPONENT =
      new RawComponentTranslationParser<>(TranslationParsers.STRING_PARSER, Component::text);

  public static final TranslationParser<Component> MINI_MESSAGE =
      new MiniMessageTranslationParser(MiniMessage.miniMessage());

  private AdventureTranslationParsers() {}
}
