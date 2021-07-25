package com.example.naverbookapiproject.util

import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView


//editext 키보드 검색 눌렀을때  특정뷰  click 실행되게 하는  기능
fun EditText.search(view: View) {
    this.setOnEditorActionListener(object : TextView.OnEditorActionListener {
        override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                view.performClick()
                return true
            }
            return false
        }
    })
}