/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.activevideo.keytest

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.FragmentActivity

/**
 * Loads [KeyEventFragment].
 */
class MainActivity : FragmentActivity() {

    private var lastKey : Int? = null
    private var exitKey : Int? = KeyEvent.KEYCODE_BACK // AV
    private var exitKeyDev : Int? = KeyEvent.KEYCODE_DEL // 67
    private var keyState: String = ""
    private var keyStateIcon: String = ""
    private var state: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /*
    fun extractKeyInfo (keyEvent: android.view.KeyEvent?): Object {
        val keyInfo = object {
            var keyState: String = ""
            var keyIcon: String = ""
        }
        when (keyEvent?.action) {
            KeyEvent.ACTION_DOWN -> {
                keyInfo.keyState = "KEYDOWN"
                keyInfo.keyIcon = "⬇️"
            }
            KeyEvent.ACTION_UP -> {
                keyInfo.keyState = "KEYUP"
                keyInfo.keyIcon = "⬆️️"
            }
            else -> {
                keyInfo.keyState = "UNKNOWN (" + keyEvent?.action + ")"
                keyInfo.keyIcon = keyEvent?.action.toString()
            }
        }
        return keyInfo as Object
    }
    */

    fun getKeyState (keyEvent: android.view.KeyEvent?): String {
        if (keyEvent?.action == KeyEvent.ACTION_DOWN) {
            keyState = "KEYDOWN"
        } else if (keyEvent?.action == KeyEvent.ACTION_UP) {
            keyState = "KEYUP"
        } else {
            keyState = "UNKNOWN (" + keyEvent?.action + ")"
        }
        return keyState
    }
    fun getKeyStateIcon (keyEvent: android.view.KeyEvent?): String {
        if (keyEvent?.action == KeyEvent.ACTION_DOWN) {
            keyStateIcon = "⬇️"
        } else if (keyEvent?.action == KeyEvent.ACTION_UP) {
            keyStateIcon = "⬆️"
        } else {
            keyStateIcon = keyEvent?.action.toString()
        }
        return keyStateIcon
    }
    fun getKeyIsLong (keyEvent: android.view.KeyEvent?): Boolean {
        return keyEvent?.isLongPress!!
    }
    fun getKeySymbol (keyCode: Int): String {
        return  KeyEvent.keyCodeToString(keyCode).toString()
    }
    fun getKeyHWID (keyEvent: android.view.KeyEvent?): String {
        return  keyEvent?.getScanCode().toString()
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ( (keyCode == exitKey &&  lastKey == exitKey) || (keyCode == exitKeyDev &&  lastKey == exitKeyDev) ) {
            onBackPressed()
        } else {
            (supportFragmentManager.findFragmentById(R.id.fragment_text_keyevent) as KeyEventFragment).showKeyInfo(
                keyCode,
                getKeyStateIcon(event),
                getKeyIsLong(event),
                getKeySymbol(keyCode),
                getKeyHWID(event)
            )
            (supportFragmentManager.findFragmentById(R.id.fragment_text_history) as KeyHistoryFragment).addEventItem(
                keyCode.toString() + " " + getKeyStateIcon(event) + " " + getKeySymbol(keyCode)
            )
        }
        lastKey = keyCode
        return true
    }
}
