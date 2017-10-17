# Retrofit + OKHttp
Android Network Library : HttpsURLConnection, Volley, **Retrofit + OKHttp**, Fast Android Networking

การแลกเปลี่ยนข้อมูลกับ Server เราจะได้ข้อมูลมาในรูปของไฟล์ json หรือ xml โดยส่วนมากจะใช้ json เพราะว่า เก็บข้อมูลเป็น key-value ทำให้อ่านง่าย อีกทั้งยังประหยัด bandwidth และมีตัวช่วยในการ parse ข้อมูล

> ex. json file <br>

```json
{
  "name": "Bell",
  "age": 21,
  "car": [{
      "model": "Ford"
    },
    {
      "model": "BMW"
    }] 
}
```



# Lab

**method ในการรับ-ส่งข้อมูล ผ่าน api จะใช้ GET กับ POST**

### การเชื่อมต่อ api ด้วย retrofit

1. เพิ่ม permission เชื่อมต่อ Internet ลงใน Manifest File

    `<uses-permission android:name="android.permission.INTERNET"/>`

2. Add Retrofit Library  ลงใน build.gradle ของ App

   ```
   compile 'com.squareup.retrofit2:retrofit:2.3.0'
   compile 'com.google.code.gson:gson:2.8.1'
   compile 'com.squareup.retrofit2:converter-scalars:2.3.0'
   compile 'com.squareup.retrofit2:converter-gson:2.3.0' 
   ```

3. สร้าง Interface API
  ```java
  public interface Api {
  	String BASE = "https://us-central1-retrofit-course.cloudfunctions.net";
  	@GET("/getProfile")
  	Call<UserProfile> getProfile(@Query("user") String user);
  } 
  ```

4. สร้าง Method สำหรับดึงข้อมูลจาก API (GET)
```java
private void getUserProfile(String name) {
 	OkHttpClient client = new OkHttpClient.Builder().build();
 	Retrofit retrofit = new Retrofit.Builder()
			.baseUrl(Api.BASE)
			.client(client)
			.addConverterFactory(GsonConverterFactory.create())
			.build();
 	Api api = retrofit.create(Api.class);
 	api.getProfile(name).enqueue(new retrofit2.Callback<UserProfile>() {
 		@Override
 		public void onResponse(retrofit2.Call<UserProfile> call, retrofit2.Response<UserProfile> response) 
 		{
 			Log.d(TAG, "onResponse: " + response.body());
 		}
		@Override
 		public void onFailure(retrofit2.Call<UserProfile> call, Throwable t)
		{
 			Log.d(TAG, "onFailure");
 		}
	});
}
```
5. ใช้ GSON Mapping โดยการจับคู่ตัวแปรบน json file กับ ตัวแปรบน model class

**JSON Data**

```json
{
	"bio": "The official Instagram for Android.",
	"follower": 1412,
	"following": 7643,
	"isFollow": "false",
	"post": 23,
	"posts": [],
	"urlProfile": "https://lh3.googleusercontent.com/DE1k9KAl2teMTpbb1",
	"user": "android"
}
```

**Java Class**
```java
public class UserProfile {
	private String bio;
	private int follower;
	private int following;
	private boolean isFollow;
	private int post;
	private String urlProfile;
	private String user;
	//Setter & Getter
}
```

6. ทำการแสดงผลโดยดึงข้อมูลจาก `response.body()`





### การแสดงรูปจาก URL โดยโหลดจาก Internet

1. เพิ่ม library repo ที่ไฟล์ build.gradle ของ Project

   ```
   allprojects {
   	repositories {
   		jcenter()
   		mavenCentral()
   		maven { url 'https://maven.google.com' }
   	}
   } 
   ```

2. Add dependency ที่ไฟล์ build gradle ของ App
  ```
  compile 'com.github.bumptech.glide:glide:4.1.1'
  annotationProcessor 'com.github.bumptech.glide:compiler:4.1.1' 
  ```
3. วิธีเรียกใช้
  ```java
  ImageView imageProfile = findViewById(R.id.imageProfile);
  Glide.with(this).load(userProfile.getUrlProfile()).into(imageProfile);
  ```

<img src="https://github.com/wirunpong-j/course-android-kmitl/blob/master/lab/lab07/wiki/img1.png" width="346" height="700">

### วิธีแสดงรูปภาพในรูปแบบ Grid ด้วย RecycleView
1. Add dependency ที่ไฟล์ build.gradle ของ App
  ```
  compile 'com.android.support:recyclerview-v7:26.1.0' 
  ```

2. เพิ่ม layout ลงใน .xml

   ```xml
   <android.support.v7.widget.RecyclerView
   	android:id="@+id/list"
   	android:layout_width="match_parent"
   	android:layout_height="0dp"
   	android:layout_marginTop="8dp"
   	android:layout_weight="1" />
   ```
3. เพิ่ม layout ใหม่ (ชิ้นส่วนย่อยๆ) ลงในไฟล์ .xml ใหม่

  ```xml
  <android.support.constraint.ConstraintLayout
   	xmlns:android="http://schemas.android.com/apk/res/android"
   	xmlns:app="http://schemas.android.com/apk/res-auto"
   	android:layout_width="match_parent"
   	android:layout_height="wrap_content">
   	<ImageView
   		android:id="@+id/imageView"
   		android:layout_width="0dp"
   		android:layout_height="0dp"
   		android:layout_margin="1dp"
   		android:background="#eff0f1"
   		android:scaleType="centerCrop"
   		app:layout_constraintBottom_toBottomOf="parent"
   		app:layout_constraintDimensionRatio="H,1:1"
   		app:layout_constraintLeft_toLeftOf="parent"
   		app:layout_constraintRight_toRightOf="parent"
   		app:layout_constraintTop_toTopOf="parent" />
  </android.support.constraint.ConstraintLayout> 
  ```
4. สร้าง Adapter Class สำหรับการ Set ค่าลงไปเพื่อนำไปให้ RecycleView และแสดงผล

  ```java
  public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder>{
   	private Activity activity;
   	private List<PostModel> data;
    
   	public PostAdapter(Activity activity) {
   		this.activity = activity;
   		data = new ArrayList<>();
   	}
   	public void setData(List<PostModel> data) {
   		this.data = data;
   	}
    
   	@Override
   	public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
   		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
   		Holder holder = new Holder(itemView);
   		return holder;
   	}
    
   	@Override
   	public void onBindViewHolder(Holder holder, int position) {
   		String imageUrl = data.get(position).getUrl();
   		Glide.with(activity).load(imageUrl).into(holder.imageView);
   	}
    
   	@Override
   	public int getItemCount() {
   		return data.size();
   	}
    
    	static class Holder extends RecyclerView.ViewHolder {
   		ImageView imageView;
   		public Holder(View itemView) {
   			super(itemView);
   			imageView = itemView.findViewById(R.id.imageView);
   		}
   	}
  } 
  ```
5. แสดงผลที่ RecycleView
  ```java
  RecyclerView list = findViewById(R.id.list);
  list.setLayoutManager(new GridLayoutManager(this, 3));
  PostAdapter adapter = new PostAdapter(this);
  adapter.setData(userProfile.getPosts());
  list.setAdapter(adapter); 
  ```

<img src="https://github.com/wirunpong-j/course-android-kmitl/blob/master/lab/lab07/wiki/img2.gif" width="346" height="700">



#Homework

**1. App สามารถเลือก user ได้ 3 account (android, nature, cartoon)**

<img src="https://github.com/wirunpong-j/course-android-kmitl/blob/master/lab/lab07/wiki/img3.gif" width="346" height="700">

**2. แสดงรายการรูป ให้เพิ่มปุ่มสลับแบบ Grid กับ List ได้ และในรูปแต่ละรูปให้แสดงจำนวน Like และ Comment ด้วย**

<img src="https://github.com/wirunpong-j/course-android-kmitl/blob/master/lab/lab07/wiki/img4.gif" width="346" height="700">

**3. เพิ่ม Feature Follow เมื่อกดแล้วต้อง loading  เพื่อรอ**

<img src="https://github.com/wirunpong-j/course-android-kmitl/blob/master/lab/lab07/wiki/img5.gif" width="346" height="700">



