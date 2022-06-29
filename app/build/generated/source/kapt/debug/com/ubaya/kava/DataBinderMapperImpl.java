package com.ubaya.kava;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.ubaya.kava.databinding.BookListItemBindingImpl;
import com.ubaya.kava.databinding.FragmentBookDetailBindingImpl;
import com.ubaya.kava.databinding.FragmentCreateBookBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_BOOKLISTITEM = 1;

  private static final int LAYOUT_FRAGMENTBOOKDETAIL = 2;

  private static final int LAYOUT_FRAGMENTCREATEBOOK = 3;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(3);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ubaya.kava.R.layout.book_list_item, LAYOUT_BOOKLISTITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ubaya.kava.R.layout.fragment_book_detail, LAYOUT_FRAGMENTBOOKDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ubaya.kava.R.layout.fragment_create_book, LAYOUT_FRAGMENTCREATEBOOK);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_BOOKLISTITEM: {
          if ("layout/book_list_item_0".equals(tag)) {
            return new BookListItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for book_list_item is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTBOOKDETAIL: {
          if ("layout/fragment_book_detail_0".equals(tag)) {
            return new FragmentBookDetailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_book_detail is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCREATEBOOK: {
          if ("layout/fragment_create_book_0".equals(tag)) {
            return new FragmentCreateBookBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_create_book is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(3);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "book");
      sKeys.put(2, "listener");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(3);

    static {
      sKeys.put("layout/book_list_item_0", com.ubaya.kava.R.layout.book_list_item);
      sKeys.put("layout/fragment_book_detail_0", com.ubaya.kava.R.layout.fragment_book_detail);
      sKeys.put("layout/fragment_create_book_0", com.ubaya.kava.R.layout.fragment_create_book);
    }
  }
}
