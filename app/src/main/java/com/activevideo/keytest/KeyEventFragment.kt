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
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment

class KeyEventFragment : Fragment() {

    private lateinit var tvKeyEvent: TextView
    private lateinit var tvKeySymbol: TextView
    private lateinit var tvKeyState: TextView
    private lateinit var tvKeyHWId: TextView
    private var keyState: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_key_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvKeyEvent = view.findViewById(R.id.text_keyevent)
        tvKeySymbol = view.findViewById(R.id.text_keysymbol)
        tvKeyState = view.findViewById(R.id.text_keystate)
        tvKeyHWId = view.findViewById(R.id.text_keyhwid)
        tvKeyState.text = "Press a key to start..."
        tvKeyHWId.text = "Press BACK key twice to exit"
    }

    fun showKeyInfo(key: Int, keyState: String, isLongPress: Boolean, keySymbol: String, keyHWID: String) {
        tvKeyEvent.text = key.toString()
        tvKeyState.text = keyState
//        tvKeyState.text = keyState + "("+ isLongPress.toString() + ")"
        tvKeySymbol.text = keySymbol
        tvKeyHWId.text = "Hardware key ID: " + keyHWID
    }
}
