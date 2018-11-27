package com.example.danielhorowitz.clean

import android.app.Activity
import android.support.v4.app.Fragment

/**
 * Created by danielhorowitz on 8/10/17.
 */
class NavigatorImpl(
    private val activity: Activity,
    private val fragment: Fragment? = null
) : Navigator {
    constructor(fragment: Fragment) : this(fragment.activity as Activity, fragment)
}