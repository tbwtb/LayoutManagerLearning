package com.shifang.layoutmanagerlearning

import androidx.recyclerview.widget.RecyclerView

/**
 *Created by zjy on 2021/7/12.
 */
class CustomLayoutManager : RecyclerView.LayoutManager() {
    var totalItemHeight = 0
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT)
    }

    override fun canScrollVertically(): Boolean {
        return true
    }

    var total = 0
    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        if (itemCount == 0) {
            return 0
        }
        var travel = dy
        if (total + dy < 0) {
            travel = -total
        } else if (total + dy > totalItemHeight - getVerticalSpace()) {
            //totalItemHeight是所有item的高度，height是当前一屏的高度
            travel = totalItemHeight - total - getVerticalSpace()
        }
        total += travel
        offsetChildrenVertical(-travel)
        return dy
    }

    private fun getVerticalSpace(): Int {
        println("$height ::: paddingBottom = $paddingBottom ::: paddingTop = $paddingTop")
        return height - paddingBottom - paddingTop
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        super.onLayoutChildren(recycler, state)
        var offset = 0
        for (i in 0 until itemCount) {
            val item = recycler.getViewForPosition(i)
            measureChildWithMargins(item, 0, 0);
            val width = getDecoratedMeasuredWidth(item)
            val height = getDecoratedMeasuredHeight(item)
            addView(item)
            layoutDecorated(item, 0, offset, width, height + offset)
            offset += height
        }
        totalItemHeight = offset.coerceAtLeast(getVerticalSpace())
        println("totalItemHeight = $totalItemHeight")
    }

}