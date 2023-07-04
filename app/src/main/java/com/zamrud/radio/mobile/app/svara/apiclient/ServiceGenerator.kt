package com.zamrud.radio.mobile.app.svara.apiclient

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.zamrud.radio.mobile.app.svara.apiclient.services.CurationService
import com.zamrud.radio.mobile.app.svara.apiclient.services.DocumentService
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okio.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class ServiceGenerator {
    companion object {
        const val API_BASE_URL = "https://api.svara.id/dev/"
        const val PAYMENT_BASE_URL = "https://payment-dev.svara.fm/" //webview
        const val PAYMENT_API_BASE_URL = "https://api.svara.id/payment/dev/" //api payment
        const val REFERRAL_BASE_URL = "https://api.svara.id/referral/dev/"
        const val ADDITIONAL_FEATURES_BASE_URL = "https://api.svara.id/additional-features/dev/"
        const val ANALYTICS_BASE_URL = "https://api.svara.id/analytics/dev/"
        const val API_BASE_ADS = "https://api.svara.id/click/dev/"
        const val API_BASE_AUDIO = "https://api.svara.id/v1/audio/svara-music/download/"
        const val API_LOCAL_URL = "http://192.168.1.12:3000/api/"
        const val API_BACKUP_URL = "http://apibeta.svara.id:3000/api/"
        const val API_AUTH = "http://apibeta.svara.id:3000/auth/"
        const val API_BACKUP_2_URL = "http://180.250.18.114:3000/api/"
        const val API_COUNTLY = "https://countly.svara.id"
        //    public static final String API_SOCKET_URL = "https://aws-socket.svara.id/radio";
        const val API_SOCKET_URL = "https://socket-dev.svara.id"
        const val API_SOCKET_RADIO = "$API_SOCKET_URL/radio"
        const val API_SOCKET_DIRECT = "$API_SOCKET_URL/direct"
        const val API_SOCKET_AIRTIME = "$API_SOCKET_URL/airtime"

        const val REPORT_EMAIL = "report@svara.fm"

        const val LEGAL_NAME = "Svara"
        const val BASE_LEGAL_URL = "https://legal.svara.id/"
        const val LEGAL_WELCOME = "SVARA Platform"
        const val LEGAL_COMPANY = "PT. SVARA Inovasi Indonesia"
        const val LEGAL_EMAIL = "report@svara.fm"

        const val ARTICLE_SCHEME = "https"
        const val ARTICLE_DOMAIN = "articles.svara.id"
        const val ARTICLE_BASE = "$ARTICLE_SCHEME://$ARTICLE_DOMAIN"


        //    public static final String TERM_SERVICE_URL_ = BASE_LEGAL_URL + "terms-of-services/" + createLegalParams();
        //    public static final String PRIVACY_POLICY_URL_ = BASE_LEGAL_URL + "privacy-policy/" + createLegalParams();
        //    public static final String COMMUNITY_GUIDE_URL_ = BASE_LEGAL_URL + "community-guidelines/" + createLegalParams();

        fun getTermServiceUrl(locale: String): String {
            return if (locale == "in")
                BASE_LEGAL_URL + "terms-of-services/id/" + createLegalParams()
            else BASE_LEGAL_URL + "terms-of-services/en/" + createLegalParams()
        }

        fun getPrivacyPolicyUrl(locale: String): String {
            return if (locale == "in")
                BASE_LEGAL_URL + "privacy-policy/id/" + createLegalParams()
            else BASE_LEGAL_URL + "privacy-policy/en/" + createLegalParams()
        }

        fun getCommunityGuideUrl(locale: String): String {
            return if (locale == "in")
                BASE_LEGAL_URL + "community-guidelines/id/" + createLegalParams()
            else BASE_LEGAL_URL + "community-guidelines/en/" + createLegalParams()
        }

        fun getUserGeneratedContentUrl(locale: String): String {
            return if (locale == "in")
                BASE_LEGAL_URL + "user-generated-content/id/" + createLegalParams()
            else BASE_LEGAL_URL + "user-generated-content/en/" + createLegalParams()
        }

        private fun createLegalParams(): String {
            return LEGAL_NAME +
                    "?" +
                    "welcome=" +
                    LEGAL_WELCOME +
                    "&" +
                    "company=" +
                    LEGAL_COMPANY +
                    "&" +
                    "email=" +
                    LEGAL_EMAIL
        }

        private const val BASE_LOCAL_URL = "http://127.0.0.1:8080/"

        fun getActiveApiURL(): String {
            return API_BASE_URL
        }

        fun getActiveApiAuthURL(): String {
            return API_AUTH
        }

        fun <S> createService(serviceClass: Class<S>): S {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
//            val gson: Gson = GsonBuilder()
//                .registerTypeAdapter(DataListModel::class.java, DatalistSerializer())
//                .create()

            val retrofit: Retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(getActiveApiURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(serviceClass)
        }

        fun <S> createServiceAppAuth(serviceClass: Class<S>, appToken: String?): S {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val gson: Gson = createGsonInstance()

            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    //TODO: Handle error in interceptor
                    val originalRequest: Request = chain.request()
                    val request: Request = originalRequest.newBuilder()
                        .addHeader("Authorization", "Bearer $appToken")
                        .build()
                    val r: Response = chain.proceed(request);
                    return@Interceptor r
                })
                .addInterceptor(interceptor)
                .build()

            val retrofit: Retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(getActiveApiURL())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(serviceClass)
        }

        fun createGsonInstance(): Gson {
            val gson: Gson = GsonBuilder().create()
//                .registerTypeAdapter(SearchResult::class.java, SearchResultDeserializer())
//                .registerTypeAdapter(DataListModel::class.java, DatalistSerializer())
//                .registerTypeAdapter(TtxCurationModel::class.java, TtxDatalistSerializer())
//                .registerTypeAdapter(ListPreferenceModel::class.java, PreferenceListSerializer())
//                .registerTypeAdapter(Video::class.java, VideoDeserializer())
//                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
//                .registerTypeAdapter(UserActivities::class.java, ActivitiesSerializer())
//                .create()

            return gson
        }

        fun <S> createServiceWithAuth(serviceClass: Class<S>, context: Context?): S {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val gson: Gson = createGsonInstance()

            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    //TODO: Handle error in interceptor
                    val originalRequest: Request = chain.request()
                    val request: Request = originalRequest.newBuilder()
                        .addHeader("Authorization", "Bearer " + AuthenticationUtils.getToken(context))
                        .build()
                    val r: Response = chain.proceed(request)
                    return@Interceptor r
                })
                .addInterceptor(interceptor)
                .build()

            val retrofit: Retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(getActiveApiURL())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(serviceClass)
        }

        fun <S> createServiceAdditionalFeatures(serviceClass: Class<S>, context: Context): S {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val gson: Gson = createGsonInstance()

            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    //TODO: Handle error in interceptor
                    val originalRequest: Request = chain.request()
                    val request: Request = originalRequest.newBuilder()
                        .addHeader("Authorization", "Bearer " + AuthenticationUtils.getToken(context))
                        .build()
                    val r: Response = chain.proceed(request)
                    return@Interceptor r
                })
                .addInterceptor(interceptor)
                .build()

            val retrofit: Retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ADDITIONAL_FEATURES_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(serviceClass)
        }

        fun <S> createServiceAnalytics(serviceClass: Class<S>, context: Context): S {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val gson: Gson = createGsonInstance()

            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    //TODO: Handle error in interceptor
                    val originalRequest: Request = chain.request()
                    val request: Request = originalRequest.newBuilder()
                        .addHeader("Authorization", "Bearer " + AuthenticationUtils.getToken(context))
                        .build()
                    val r: Response = chain.proceed(request)
                    return@Interceptor r
                })
                .addInterceptor(interceptor)
                .build()

            val retrofit: Retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ANALYTICS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(serviceClass)
        }

        fun <S> createServiceUpload(serviceClass: Class<S>, context: Context): S {
            val gson: Gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create()
            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    //TODO: Handle error in interceptor
                    val originalRequest: Request = chain.request()
                    val request: Request = originalRequest.newBuilder()
                        .addHeader("Authorization", "Bearer " + AuthenticationUtils.getToken(context))
                        .addHeader("Connection", "keep-alive")
                        .build()
                    val r: Response = chain.proceed(request)
                    return@Interceptor r
                }).connectTimeout(12, TimeUnit.HOURS)
                .readTimeout(5, TimeUnit.HOURS)
                .writeTimeout(5, TimeUnit.HOURS)
                .build()

            val retrofit: Retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(getActiveApiURL())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(serviceClass)
        }

        fun <S> createRssService(serviceClass: Class<S>, context: Context): S {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val gson: Gson = createGsonInstance()

            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    //TODO: Handle error in interceptor
                    val originalRequest: Request = chain.request()
                    val request: Request = originalRequest.newBuilder()
                        .addHeader("Authorization", "Bearer " + AuthenticationUtils.getToken(context))
                        .build()
                    val r: Response = chain.proceed(request)
                    return@Interceptor r
                })
                .addInterceptor(interceptor)
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.HOURS)
                .build()

            val retrofit: Retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(getActiveApiURL())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(serviceClass)
        }

        fun <S> createServiceReferral(serviceClass: Class<S>, context: Context): S {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

//        gson: Gson = createGsonInstance();

            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    //TODO: Handle error in interceptor
                    val originalRequest: Request = chain.request()
                    val request: Request = originalRequest.newBuilder()
                        .addHeader("Authorization", "Bearer " + AuthenticationUtils.getToken(context))
                        .build()
                    val r: Response = chain.proceed(request)
                    return@Interceptor r
                })
                .addInterceptor(interceptor)
                .build()
            val gson: Gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create()
            val retrofit: Retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(REFERRAL_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(serviceClass)
        }

        fun <S> createServicePayment(serviceClass: Class<S>, context: Context): S {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

//          gson: Gson = createGsonInstance();
            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    //TODO: Handle error in interceptor
                    val originalRequest: Request = chain.request()
                    val request: Request = originalRequest.newBuilder()
                        .addHeader("Authorization", "Bearer " + AuthenticationUtils.getToken(context))
                        .build()
                    val r: Response = chain.proceed(request)
                    return@Interceptor r
                })
                .addInterceptor(interceptor)
                .build()
            val gson: Gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create()
            val retrofit: Retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(PAYMENT_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(serviceClass)
        }

        /*public static <S> S createServiceWithAppAuth(Class<S>  serviceClass, final Context context){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        //TODO: Handle error in interceptor
                        Request originalRequest = chain.request();

                        Request request = originalRequest.newBuilder()
                                .addHeader("Authorization", AuthenticationUtils.getAppToken(context))
                                .build();
                        Response r = chain.proceed(request);
                        return r;
                    }
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(getActiveApiURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }*/
        fun createCurationService(context: Context?): CurationService {
//        okHttpClient = OkHttpClient.Builder().build();
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    //TODO: Handle error in interceptor
                    val originalRequest: Request = chain.request()
                    val request: Request = originalRequest.newBuilder()
                        .addHeader("Authorization", "Bearer " + AuthenticationUtils.getToken(context))
                        .build()
                    val r: Response = chain.proceed(request)
                    return@Interceptor r
                })
                .addInterceptor(interceptor)
                .build()

            val gson: Gson = createGsonInstance()

            val retrofit: Retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(getActiveApiURL())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(CurationService::class.java)
        }

        fun <S> connectFacebook(name: Class<S>, context: Context, appToken: String?): S {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
                .addInterceptor(Interceptor { chain ->
                    val originalRequest: Request = chain.request()
                    val request: Request = originalRequest.newBuilder()
                        .addHeader("Authorization", "Bearer $appToken")
                        .build()
                    val requestBuilder: Request.Builder = request.newBuilder()
                    requestBuilder.method(request.method, request.body)
                    val response: Response = chain.proceed(requestBuilder.build())
                    return@Interceptor response
                })
                .addInterceptor(interceptor)
                .build()

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(getActiveApiURL())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(name)
        }

        fun <S> createServiceAds(serviceClass: Class<S>, context: Context): S {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val gson: Gson = createGsonInstance()

            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    //TODO: Handle error in interceptor
                    val originalRequest: Request = chain.request()
                    val request: Request = originalRequest.newBuilder()
                        .addHeader("Authorization", "Bearer " + AuthenticationUtils.getToken(context))
                        .build()
                    val r: Response = chain.proceed(request)
                    return@Interceptor r
                })
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()

            val retrofit: Retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(API_BASE_ADS)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(serviceClass)
        }

        fun <S> createServiceAudio(serviceClass: Class<S>, context: Context, url: String, progressListener: ProgressListener): S {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val gson: Gson = createGsonInstance()

            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    //TODO: Handle error in interceptor
                    val originalRequest: Request = chain.request()
                    val builder: Request.Builder = originalRequest.newBuilder()
                    if (url.startsWith("https://")) {
                        builder.addHeader("Authorization", "Bearer " + AuthenticationUtils.getToken(context))
                    }
                    val request: Request = builder.build()
                    val r: Response = chain.proceed(request)
                    return@Interceptor r
                })
                .addInterceptor(interceptor)
                .addNetworkInterceptor(Interceptor { chain ->
                    val originalResponse: Response = chain.proceed(chain.request())
                    return@Interceptor originalResponse.newBuilder()
                        .body(ProgressResponseBody(originalResponse.body, progressListener))
                        .build()
                })
                .build()

            val retrofit: Retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(getActiveApiURL())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(serviceClass)
        }

        fun createServiceDownloadDocument(context: Context, progressListener: ProgressListener): DocumentService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val gson: Gson = createGsonInstance()

            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(Interceptor { chain ->
                    val originalResponse: Response = chain.proceed(chain.request())
                    return@Interceptor originalResponse.newBuilder()
                        .body(ProgressResponseBody(originalResponse.body, progressListener))
                        .build()
                }).connectTimeout(15, TimeUnit.SECONDS)
                .build()

            val retrofit: Retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(getActiveApiURL())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(DocumentService::class.java)
        }

        fun getLocalStream(id: String): String {
            return BASE_LOCAL_URL + "stream/" + id
        }

        fun getLocalImage(id: String): String {
            return BASE_LOCAL_URL + "image/" + id
        }

        private class ProgressResponseBody(private val responseBody: ResponseBody?, private val progressListener: ProgressListener) : ResponseBody() {
            private var bufferedSource: BufferedSource? = null

            override fun contentType(): MediaType? {
                return responseBody?.contentType()
            }

            override fun contentLength(): Long {
                return responseBody!!.contentLength()
            }

            override fun source(): BufferedSource {
                if (bufferedSource == null) {
                    bufferedSource = source(responseBody!!.source()).buffer()
                }
                return bufferedSource!!
            }

            private fun source(source: Source): Source {
                return object : ForwardingSource(source) {
                    var totalBytesRead = 0L
                    var count = 0

                    @Throws(IOException::class)
                    override fun read(sink: Buffer, byteCount: Long): Long {
                        val bytesRead: Long = super.read(sink, byteCount)
                        Log.e("blabla", "" + responseBody!!.byteStream().available())
                        totalBytesRead += if (bytesRead != -1L) bytesRead else 0
                        // read() returns the number of bytes read, or -1 if this source is exhausted.
                        progressListener.update(sink, bytesRead, responseBody.contentLength(), totalBytesRead, bytesRead == -1L, count)
                        count++
                        return bytesRead
                    }
                }
            }
        }
    }

    interface ProgressListener {
        fun update(sink: Buffer?, bytesRead: Long, contentLength: Long, totalBytesRead: Long, done: Boolean, count: Int)
    }
}