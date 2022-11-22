package no.ntnu.cardsnap.fx;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to control pagination over a set of items.
 *
 * @param <T> Element type to paginate over
 * @author matsjla
 */
public class Pagination<T> {
  /**
   * The items to paginate over.
   */
  private List<T> items;
  /**
   * The amount of items per page.
   */
  private final int pageSize;
  /**
   * The current page.
   */
  private int page = 0;

  /**
   * Create a new {@link Pagination}.
   *
   * @param paginationItems Initial items
   * @param maxPageSize     Number of elements per page
   */
  public Pagination(List<T> paginationItems, int maxPageSize) {
    items = new ArrayList<>(paginationItems);
    pageSize = maxPageSize;
  }

  /**
   * Get the elements that are currently visible on the page.
   *
   * @return The elements that should be shown for the current page
   */
  public List<T> getVisibleItems() {
    List<T> visible = new ArrayList<>();
    for (int i = page * pageSize;
         i < page * pageSize + pageSize && i < items.size();
         i++
    ) {
      visible.add(items.get(i));
    }
    return visible;
  }

  /**
   * Update the items inside the span, capping the page if necessary.
   *
   * @param newItems New items to replace old
   */
  public void setItems(List<T> newItems) {
    items = new ArrayList<>(newItems);
    page = Math.min(page, newItems.size() / pageSize);
  }

  /**
   * Increment to next page.
   */
  public void next() {
    page = Math.min(page + 1, items.size() / pageSize);
  }

  /**
   * Determine if there is a next page.
   *
   * @return If there is a next page
   */
  public boolean hasNext() {
    return items.size() > (page + 1) * pageSize;
  }

  /**
   * Decrement to previous page.
   */
  public void previous() {
    page = Math.max(page - 1, 0);
  }

  /**
   * Determine if there is a previous page.
   *
   * @return If there is a previous page
   */
  public boolean hasPrevious() {
    return page - 1 >= 0;
  }

  /**
   * Get current page number.
   *
   * @return The current page
   */
  public int getPage() {
    return page;
  }
}
