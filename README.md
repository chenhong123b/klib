KLIB for Android
====

数据、网络请求、图片处理

				.android-async-http-1.4.3.jar
				.sqlite-simple-2.4.jar
				.universal-image-loader-1.8.4.jar
				.gson-2.2.4.jar
				.android-support-v4.jar

====

<h3>KDbClient:</h3>

一.extends KApp,实现creatDbTables():

	public class MyApp extends KApp {
	
		@Override
		protected void creatDbTables() {
		 
			SQLiteSimple databaseSimple = new SQLiteSimple(this); 
			    
			    //or
			    //new SQLiteSimple(context, DatabaseName);
			    //new SQLiteSimple(context, DataVersion);
			    
			databaseSimple.create(Record.class);
	
		}
	}

   修改AndroidManifest.xml application节点为自己的类(for example: android:name="com.kooxiv.example.MyApp").

二.修改Model:
	
	public class Record {
		public transient static final String COLUMN_RECORD_TEXT = "text";
		public transient static final String COLUMN_ID = "_id";
	
		@Column(name = COLUMN_ID, type = ColumnType.INTEGER, isPrimaryKey = true, isAutoincrement = true)
		public int id;
	
		//@SerializedName("text")
		@Column
		public String text;
	
		//@SerializedName("number")
		@Column
		public int number;
		
		//@SerializedName is for Gson
	}
	
三.使用:

	KDbClient<Record> dbClient = new KDbClient<Record>(this, Record.class);

	//增************************************
		Record record = new Record();
		record.text = "record";
		//可重复添加记录
		dbClient.create(record);
		
		//检查Record.COLUMN_RECORD_TEXT这列是否存在record.text这个值,有则不添加记录
		dbClient.createIfNotExist(record, Record.COLUMN_RECORD_TEXT, record.text);
		
		//dbClient.createIfNotExist(object, COLUMN_NAME1, COLUMN_VALUE1,COLUMN_NAME2, COLUMN_VALUE2);
		...........
	
	//查**************
		List<Record> list = dbClient.readAllDesc();
		dbClient.read(id);
		dbClient.readWhere(columnName, columnValue);
		.......
	
	//删*****************
		dbClient.deleteAll();
		dbClient.deleteWhere(columnName, columnValue);
		..............
	
	//改*************
		dbClient.update(id, newObject);
		dbClient.update(columnName, columnValue, newObject);
		..........
	
	
	
====

<h3>KHttpClient</h3>


				Params params = new Params();
		
				// params.put(KEY, Value)
				params.put("username", "111@gmail.com");
				params.put("password", "123456");
		
				// GET
				KHttpClient<Record> httpClient = new KHttpClient<Record>(this,
						Record.class) {
						
					@Override
					protected void onSuccess(int statusCode, Record entity) {
		
						if (statusCode == 200) {
							// ...........
						}
						super.onSuccess(statusCode, entity);
		
					}
				};
		
				// httpClient.setTimeout(20*1000);
				//httpClient.setUserAgent("android httpclient");
				
				httpClient.get("http://www.www.com", params);
		
				// POST
				
				params.put("file", new File("path"));
				params.put("inputstream",
						getResources().openRawResource(R.raw.uploadfile));
				httpClient.post("http://www.www.com", params);

	






...
