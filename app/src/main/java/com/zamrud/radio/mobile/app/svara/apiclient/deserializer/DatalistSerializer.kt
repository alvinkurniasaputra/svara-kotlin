package com.zamrud.radio.mobile.app.svara.apiclient.deserializer
//
//import com.google.gson.*
//import com.google.gson.reflect.TypeToken
//import com.zamrud.radio.mobile.app.svara.apiclient.model.DataListModel
//import com.zamrud.radio.mobile.app.svara.apiclient.model.ModelWithAction
//import com.zamrud.radio.mobile.app.svara.apiclient.model.classRoom.ClassRoomInDataList
//import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.*
//import com.zamrud.radio.mobile.app.svara.apiclient.model.video.Video
//import timber.log.Timber
//import java.lang.reflect.Type
//import java.util.*
//import kotlin.collections.ArrayList
//
//class DatalistSerializer : JsonDeserializer<DataListModel> {
//
//    @Throws(JsonParseException::class)
//    override fun deserialize(json: JsonElement?, typeOfT: Type, context: JsonDeserializationContext): DataListModel {
//        val out = DataListModel()
//
//        val jsonObject: JsonObject? = json?.asJsonObject
//
//        val contentTypeJsonElement: JsonElement? = jsonObject?.get("contentType")
//        val hasNextJsonElement: JsonElement? = jsonObject?.get("hasNext")
//        val countJsonElement: JsonElement? = jsonObject?.get("count")
//        val totalCountJsonElement: JsonElement? = jsonObject?.get("totalCount")
//
//        if (contentTypeJsonElement != null && contentTypeJsonElement.isJsonPrimitive) {
//            val contentType = contentTypeJsonElement.asString
//
//            out.setContentType(contentType)
//            if (hasNextJsonElement != null)
//                out.setHasNext(hasNextJsonElement.asBoolean)
//            if (countJsonElement != null)
//                out.setCount(countJsonElement.asInt)
//            if (totalCountJsonElement != null)
//                out.setTotalCount(totalCountJsonElement.asInt)
//
//            val dataListElement: JsonElement? = jsonObject["count"]
//
//            if (dataListElement != null && dataListElement.isJsonArray) {
//                when (contentType.lowercase()) {
//                    "album" -> out.setDataList(context.deserialize(dataListElement, object : TypeToken<ArrayList<Album>>() {}.type))
//                    "music" -> out.setDataList(context.deserialize(dataListElement, object : TypeToken<ArrayList<Music>>() {}.type))
//                    "playlist" -> out.setDataList(context.deserialize(dataListElement, object : TypeToken<ArrayList<Playlist>>() {}.type))
//                    "account" -> out.setDataList(context.deserialize(dataListElement, object : TypeToken<ArrayList<Account?>?>() {}.type))
//                    "artist" -> out.setDataList(context.deserialize(dataListElement, object : TypeToken<ArrayList<Artist?>?>() {}.type))
//                    "radiocontent" -> out.setDataList(context.deserialize(dataListElement, object : TypeToken<ArrayList<RadioContent?>?>() {}.type))
//                    "radio" -> out.setDataList(context.deserialize(dataListElement, object : TypeToken<ArrayList<Radio?>?>() {}.type))
//                    "upload" -> out.setDataList(context.deserialize(dataListElement, object : TypeToken<ArrayList<Upload?>?>() {}.type))
//                    "ttx" -> out.setDataList(context.deserialize(dataListElement, object : TypeToken<ArrayList<TtxModel?>?>() {}.type))
//                    "curationpage" -> out.setDataList(context.deserialize(dataListElement, object : TypeToken<ArrayList<CurationPageModel?>?>() {}.type))
//                    "room" -> out.setDataList(context.deserialize(dataListElement, object : TypeToken<ArrayList<ChatRoom?>?>() {}.type))
//                    "link" -> out.setDataList(linkDeserialize(context, dataListElement.asJsonArray))
//                    "comment", "feedcomment" -> out.setDataList(context.deserialize(dataListElement, object : TypeToken<ArrayList<Comment?>?>() {}.type))
//                    "video" -> out.setDataList(context.deserialize(dataListElement, object : TypeToken<ArrayList<Video?>?>() {}.type))
//                    "document" -> out.setDataList(context.deserialize(dataListElement, object : TypeToken<ArrayList<Document?>?>() {}.type))
//                    "article" -> out.setDataList(context.deserialize(dataListElement, object : TypeToken<ArrayList<Article?>?>() {}.type))
//                    "classroom" -> out.setDataList(context.deserialize(dataListElement, object : TypeToken<ArrayList<ClassRoomInDataList?>?>() {}.type))
//                    else -> Timber.w("Deserializer for type %s not defined", contentType)
//                }
//            }
//        }
//        return out
//    }
//
//    private fun linkDeserialize(context: JsonDeserializationContext, dataListElement: JsonArray?): ArrayList<ModelWithAction> {
//        val modelWithActions: ArrayList<ModelWithAction> = ArrayList()
//        if (dataListElement == null || !dataListElement.isJsonArray)
//            return modelWithActions
//        for (element: JsonElement in dataListElement) {
//            if (!element.isJsonObject)
//                continue
//
//            val `object`: JsonObject = element.asJsonObject
//            val contentTypeElement: JsonElement? = `object`["contentType"]
//            if (contentTypeElement == null || !contentTypeElement.isJsonPrimitive)
//                continue
//            val contentType: String = contentTypeElement.asString
//            val content: JsonElement = `object`["content"]
//            `object`.remove("content")
//            val link: Link = context.deserialize(element, object : TypeToken<Link?>() {}.type) ?: continue
//            when (contentType.lowercase(Locale.getDefault())) {
//                "album" -> link.setContent(
//                    context.deserialize(
//                        content,
//                        object : TypeToken<Album?>() {}.type
//                    )
//                )
//                "music" -> link.setContent(
//                    context.deserialize(
//                        content,
//                        object : TypeToken<Music?>() {}.type
//                    )
//                )
//                "playlist" -> link.setContent(
//                    context.deserialize(
//                        content,
//                        object : TypeToken<Playlist?>() {}.type
//                    )
//                )
//                "account" -> link.setContent(
//                    context.deserialize(
//                        content,
//                        object : TypeToken<Account?>() {}.type
//                    )
//                )
//                "artist" -> link.setContent(
//                    context.deserialize(
//                        content,
//                        object : TypeToken<Artist?>() {}.type
//                    )
//                )
//                "radiocontent" -> link.setContent(
//                    context.deserialize(
//                        content,
//                        object : TypeToken<RadioContent?>() {}.type
//                    )
//                )
//                "radio" -> link.setContent(
//                    context.deserialize(
//                        content,
//                        object : TypeToken<Radio?>() {}.type
//                    )
//                )
//                "upload" -> link.setContent(
//                    context.deserialize(
//                        content,
//                        object : TypeToken<Upload?>() {}.type
//                    )
//                )
//                "ttx" -> link.setContent(
//                    context.deserialize(
//                        content,
//                        object : TypeToken<TtxModel?>() {}.type
//                    )
//                )
//                "curationpage" -> link.setContent(
//                    context.deserialize(
//                        content,
//                        object : TypeToken<CurationPageModel?>() {}.type
//                    )
//                )
//                "video" -> link.setContent(
//                    context.deserialize(
//                        content,
//                        object : TypeToken<Video?>() {}.type
//                    )
//                )
//                "dm" -> link.setContent(
//                    context.deserialize(
//                        content,
//                        object : TypeToken<Account?>() {}.type
//                    )
//                )
//                "document" -> link.setContent(
//                    context.deserialize(
//                        content,
//                        object : TypeToken<Document?>() {}.type
//                    )
//                )
//                "article" -> link.setContent(
//                    context.deserialize(
//                        content,
//                        object : TypeToken<Article?>() {}.type
//                    )
//                )
//                else -> Timber.w("Deserializer for type %s not defined", contentType)
//            }
//            modelWithActions.add(link)
//        }
//        return modelWithActions
//    }
//}