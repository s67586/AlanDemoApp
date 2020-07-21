package com.example.basemvvm.network

/****************************************************
 * Copyright (C) Alan Corporation. All rights reserved.
 *
 * Author: AlanLai
 * Create Date: 2020/6/10
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

enum class ErrorType {
    HTTP,
    IO, // IO
    TIMEOUT, // Socket
    UNKNOWN //Anything else
}