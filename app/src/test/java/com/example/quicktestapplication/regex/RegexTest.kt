package com.example.quicktestapplication.regex

import junit.framework.TestCase.assertEquals
import org.junit.Test

const val MA_LFS_FOLDER_NAME = "MiniApp"
const val MA_LFS_LIMIT_SIZE = 5L * 1024 * 1024 // 5MB
const val MA_LFS_SCHEMA = "https://miniprogram/MPStorage/LFS/"

const val MA_LFS_SCHEMA_CACHE = "${MA_LFS_SCHEMA}caches"
const val MA_LFS_SCHEMA_DATA = "${MA_LFS_SCHEMA}data"

const val MA_LFS_FOLDER_CACHE = "caches"
const val MA_LFS_FOLDER_DATA = "data"

val REGEX_EXTRACT_MD5_UID_CACHE = """(?<=$MA_LFS_SCHEMA).*?(?=/$MA_LFS_FOLDER_CACHE)""".toRegex()
val REGEX_EXTRACT_MD5_UID_DATA = """(?<=$MA_LFS_SCHEMA).*?(?=/$MA_LFS_FOLDER_DATA)""".toRegex()

/**
 * Regex to check a MA cache url is valid or not
 *
 * Ex: https://miniprogram/MPStorage/LFS/8eacd0abebe870c7112ce5ec4e87077e/caches/24658920939471232/f21f1fa18ab905189d6ea48fbffefde4.png
 */
val REGEX_VALID_MA_CACHE_URL =
    """^$MA_LFS_SCHEMA(?<noisedUid>[^/]+)/$MA_LFS_FOLDER_CACHE/(?<miniAppId>[^/]+)/(?<fileInfoHash>[^/]+)\.(?<extension>[^/]+)$""".toRegex()


class RegexTest {
    @Test
    fun testRegexCache() {
        val string =
            "${MA_LFS_SCHEMA}8eacd0abebe870c7112ce5ec4e87077e/$MA_LFS_FOLDER_CACHE/24658920939471232/f21f1fa18ab905189d6ea48fbffefde4.png"
        assertEquals(
            "8eacd0abebe870c7112ce5ec4e87077e",
            REGEX_EXTRACT_MD5_UID_CACHE.find(string)?.value
        )
    }

    @Test
    fun testRegexData() {
        val string =
            "${MA_LFS_SCHEMA}8eacd0abebe870c7112ce5ec4e87077e/$MA_LFS_FOLDER_DATA/24658920939471232/f21f1fa18ab905189d6ea48fbffefde4.png"
        assertEquals(
            "8eacd0abebe870c7112ce5ec4e87077e",
            REGEX_EXTRACT_MD5_UID_DATA.find(string)?.value
        )
    }

    @Test
    fun testRegexCheckValidMaCacheUrl() {
        val url =
            "${MA_LFS_SCHEMA}8eacd0abebe870c7112ce5ec4e87077e/$MA_LFS_FOLDER_CACHE/24658920939471232/f21f1fa18ab905189d6ea48fbffefde4.png"
        val url1 = "https://miniprogram/MPStorage/LFS/25bb463ef8518cc5e8de092d66cd7cd8/3481171046739208108/caches/c1286674e1c01778cb62a7ac19eff9b8.jpg"
        assertEquals(true, REGEX_VALID_MA_CACHE_URL.matches(url1))
    }

    @Test
    fun testRegexCheckValidMaCacheUrl2() {
        val url =
            "${MA_LFS_SCHEMA}8eacd0abebe870c7112ce5ec4e87077e/$MA_LFS_FOLDER_CACHE/24658920939471232/f21f1fa18ab90.5189d6e.a48fbffefde4.png"
        assertEquals(true, REGEX_VALID_MA_CACHE_URL.matches(url))
    }

    @Test
    fun testRegexCheckValidMaCacheUrl3() {
        val url =
            "https://abcxyz/8eacd0abebe870c7112ce5ec4e87077e/$MA_LFS_FOLDER_CACHE/24658920939471232/f21f1fa18ab90.5189d6e.a48fbffefde4.png"
        assertEquals(false, REGEX_VALID_MA_CACHE_URL.matches(url))
    }

    @Test
    fun testRegexCheckValidMaCacheUrl4() {
        val url =
            "https://abcxyz/8eacd0abebe870c7112ce5ec4e87077e/$MA_LFS_FOLDER_CACHE/24658920939471232/f21f1fa18ab90.5189d6e.a48fbffefde4"
        assertEquals(false, REGEX_VALID_MA_CACHE_URL.matches(url))
    }

    @Test
    fun testRegexCheckValidMaCacheUrl5() {
        val url =
            "${MA_LFS_SCHEMA}8eacd0abebe870c7112ce5ec4e87077e/$MA_LFS_FOLDER_CACHE/24658920939471232/f21f1fa18ab905189d6ea48fbffefde4.png"
        println("------------------")
        val matchResult = REGEX_VALID_MA_CACHE_URL.matchEntire(url) ?: return
        val groups = matchResult.groups
        println("groups = $groups")
        groups.forEachIndexed { idx, value ->
            println("index $idx, value = $value")
        }
        println("noisedUid = ${groups["noisedUid"]?.value}")
        println("miniAppId = ${groups["miniAppId"]?.value}")
        println("fileInfoHash = ${groups["fileInfoHash"]?.value}")
        println("extension = ${groups["extension"]?.value}")
    }
}