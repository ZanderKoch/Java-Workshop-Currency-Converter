package zander.lexicon.currencyconverter;

import java.util.List;


public class Menu<T extends MenuItem> {
    private final List<T> items;
    private final int pageSize;
    private int currentPage = 0;

    public Menu(List<T> items, int pageSize) {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Menu must be made with at least one item.");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be a positive non-zero value.");
        }
        this.items = items;
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if ((currentPage < 0) || (currentPage >= getPageCount())) {
            throw new IndexOutOfBoundsException("Page number " + currentPage + " is out of bounds.");
        }
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return (int) Math.ceil((double) items.size() / pageSize);
    }

    public List<T> getCurrentPageItems() {
        int startIndex = currentPage * pageSize;
        int endIndex = Math.min(startIndex + pageSize, items.size());
        return items.subList(startIndex, endIndex);
    }
}
