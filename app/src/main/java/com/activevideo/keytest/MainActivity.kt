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
    private var exitKey : Int? = 67 // 111
    private var keyState: String = ""
    private var keyStateIcon: String = ""
    private var state: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun extractKeyInfo (keyEvent: android.view.KeyEvent?): String {
        if (keyEvent?.action == KeyEvent.ACTION_DOWN) {
            keyState = "KEYDOWN"
            keyStateIcon = "⬇️"
        } else if (keyEvent?.action == KeyEvent.ACTION_UP) {
            keyState = "KEYUP"
            keyStateIcon = "⬆️"
        } else {
            keyState = "UNKNOWN (" + keyEvent?.action + ")"
            keyStateIcon = keyEvent?.action.toString()
        }
//        tvKeyEvent.text = keyEvent.keyCode().toString()
//        tvKeySymbol.text = KeyEvent.keyCodeToString(keyEvent.keyCode()).toString()
//        tvKeyState.text = keyState
//        tvKeyHWId.text = "Device specific ID: " + keyEvent?.getScanCode().toString()
//        keyState = keyEvent?.keyCode.toString()
//        keyState = KeyEvent.keyCodeToString(keyEvent.keyCode()).toString()
//        return Array(keyEvent.keyCode().toString(), KeyEvent.keyCodeToString(keyEvent.keyCode()).toString(), keyState,  keyEvent?.getScanCode().toString())
//        return keyEvent?.keyCode.toString()
//        return KeyEvent.keyCodeToString(keyEvent?.keyCode!!).toString()
        return keyStateIcon
    }

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


    override fun onKeyDown(keyCode: Int, event: android.view.KeyEvent?): Boolean {
//        state = extractKeyInfo(event)
        if (keyCode == exitKey && lastKey == exitKey) {
            onBackPressed()
        } else {
            (supportFragmentManager.findFragmentById(R.id.fragment_text_keyevent) as KeyEventFragment).showKeyInfo(
                keyCode,
                event
            )
            (supportFragmentManager.findFragmentById(R.id.fragment_text_history) as KeyHistoryFragment).addEventItem(
//                extractKeyInfo(event)
                keyCode.toString() + " " + extractKeyInfo(event) + " " + KeyEvent.keyCodeToString(keyCode).toString()
            )
        }
        lastKey = keyCode
        return true
    }
}
