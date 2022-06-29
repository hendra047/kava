package com.ubaya.kava.databinding;
import com.ubaya.kava.R;
import com.ubaya.kava.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class BookListItemBindingImpl extends BookListItemBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.cardView2, 4);
        sViewsWithIds.put(R.id.imageCoverBook, 5);
        sViewsWithIds.put(R.id.progressLoadImageCover, 6);
        sViewsWithIds.put(R.id.imageView, 7);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BookListItemBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }
    private BookListItemBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.cardview.widget.CardView) bindings[4]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.ImageView) bindings[7]
            , (android.widget.ProgressBar) bindings[6]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[1]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.textShowAuthor.setTag(null);
        this.textShowRating.setTag(null);
        this.textShowTitleBook.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.book == variableId) {
            setBook((com.ubaya.kava.model.Book) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setBook(@Nullable com.ubaya.kava.model.Book Book) {
        this.mBook = Book;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.book);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        double androidxDatabindingViewDataBindingSafeUnboxBookRating = 0.0;
        java.lang.Double bookRating = null;
        java.lang.String bookTitle = null;
        com.ubaya.kava.model.Book book = mBook;
        java.lang.String doubleToStringBookRating = null;
        java.lang.String bookAuthor = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (book != null) {
                    // read book.rating
                    bookRating = book.getRating();
                    // read book.title
                    bookTitle = book.getTitle();
                    // read book.author
                    bookAuthor = book.getAuthor();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(book.rating)
                androidxDatabindingViewDataBindingSafeUnboxBookRating = androidx.databinding.ViewDataBinding.safeUnbox(bookRating);


                // read Double.toString(androidx.databinding.ViewDataBinding.safeUnbox(book.rating))
                doubleToStringBookRating = java.lang.Double.toString(androidxDatabindingViewDataBindingSafeUnboxBookRating);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textShowAuthor, bookAuthor);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textShowRating, doubleToStringBookRating);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textShowTitleBook, bookTitle);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): book
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}