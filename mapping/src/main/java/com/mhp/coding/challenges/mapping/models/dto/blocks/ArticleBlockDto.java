package com.mhp.coding.challenges.mapping.models.dto.blocks;

public class ArticleBlockDto implements Comparable<ArticleBlockDto> {
    private int sortIndex;

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

    @Override
    public int compareTo(ArticleBlockDto other) {
        return Integer.compare(sortIndex, other.getSortIndex());
    }
}
