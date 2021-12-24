package org.schabi.newpipe.settings.preferencesearch;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class PreferenceSearcher implements AutoCloseable {
    private final List<PreferenceSearchItem> allEntries = new ArrayList<>();

    private final PreferenceSearchConfiguration configuration;

    PreferenceSearcher(final PreferenceSearchConfiguration configuration) {
        this.configuration = configuration;
    }

    void add(final List<PreferenceSearchItem> items) {
        allEntries.addAll(items);
    }

    List<PreferenceSearchItem> searchFor(final String keyword) {
        if (TextUtils.isEmpty(keyword)) {
            return new ArrayList<>();
        }

        return configuration.getSearchMatcher()
                .search(allEntries.stream(), keyword)
                .collect(Collectors.toList());
    }

    @Override
    public void close() {
        allEntries.clear();
    }
}
