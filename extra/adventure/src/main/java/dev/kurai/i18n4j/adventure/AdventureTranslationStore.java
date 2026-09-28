package dev.kurai.i18n4j.adventure;

import dev.kurai.i18n4j.Translation;
import dev.kurai.i18n4j.TranslationKey;
import dev.kurai.i18n4j.TranslationParser;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.translation.TranslationStore;
import org.jspecify.annotations.Nullable;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class AdventureTranslationStore implements TranslationStore<Translation> {

  private final Key name;
  private Locale locale;
  private final dev.kurai.i18n4j.TranslationStore translationStore;
  private final TranslationParser<? extends Component> translationParser;

  private AdventureTranslationStore(final Key name, final Locale locale, final dev.kurai.i18n4j.TranslationStore translationStore, final TranslationParser<? extends Component> translationParser) {
    this.name = name;
    this.locale = locale;
    this.translationStore = translationStore;
    this.translationParser = translationParser;
  }

  public static AdventureTranslationStore translationStore(final Key name, final dev.kurai.i18n4j.TranslationStore translationStore, final TranslationParser<? extends Component> translationParser) {
    return translationStore(name, Locale.getDefault(), translationStore, translationParser);
  }

  public static AdventureTranslationStore translationStore(final Key name, final Locale defaultLocale, final dev.kurai.i18n4j.TranslationStore translationStore, final TranslationParser<? extends Component> translationParser) {
    return new AdventureTranslationStore(name, defaultLocale, translationStore, translationParser);
  }

  private TranslationKey keyAsTranslationKey(final String key) {
    return TranslationKey.translationKey(key);
  }

  @Override
  public boolean contains(String key) {
    return contains(key, locale);
  }

  @Override
  public boolean contains(String key, Locale locale) {
    return translationStore.exists(keyAsTranslationKey(key), locale);
  }

  @Override
  public void defaultLocale(Locale locale) {
    this.locale = locale;
  }

  @Override
  public void register(String key, Locale locale, Translation translation) {
    if (!translationStore.exists(keyAsTranslationKey(key), locale)) {
      translationStore.insertOne(translation);
    } else {
      translationStore.updateOne(translation);
    }
  }

  @Override
  public void registerAll(Locale locale, Map<String, Translation> translations) {
    for (Map.Entry<String, Translation> entry : translations.entrySet()) {
      register(entry.getKey(), locale, entry.getValue());
    }
  }

  @Override
  public void registerAll(Locale locale, Set<String> keys, Function<String, Translation> function) {
    for (String key : keys) {
      register(key, locale, function.apply(key));
    }
  }

  @Override
  public void unregister(String key) {
    translationStore.deleteByKey(keyAsTranslationKey(key));
  }

  @Override
  public Key name() {
    return name;
  }

  @Override
  public @Nullable MessageFormat translate(String key, Locale locale) {
    return null;
  }

  @Override
  public @Nullable Component translate(TranslatableComponent component, Locale locale) {
    final TranslationKey key = keyAsTranslationKey(component.key());
    final Translation translation = translationStore.findByKeyAndLocale(key, locale);
    return translationParser.parse(translation);
  }
}
