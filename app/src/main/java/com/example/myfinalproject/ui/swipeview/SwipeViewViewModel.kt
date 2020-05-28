package com.example.myfinalproject.ui.swipeview

import androidx.lifecycle.ViewModel

class SwipeViewViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    class Home {
        var title: String? = null
        var image = 0
        var detail: String? = null

        constructor(title: String?, image: Int) {
            this.title = title
            this.image = image
            detail = detail
        }

        constructor() {}

    }
}