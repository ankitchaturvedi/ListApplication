## ListAssignment
A bit overview, it uses API to get infoData and get JSON data back. Then it is parsed using google's gson library and the requests are sent by Retrofit.
In this project show info data into recyclerview by use android MVVM design pattern.Glide is use for fast and efficient image loading.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Dependencies

Enable DataBiding in app/build.gradle. Also add the RecyclerView, Glide and retrofit2 dependencies and Sync the project.

```
android {
     buildFeatures{
        dataBinding = true
    }
}
```

```
 // retrofit
    implementation 'com.squareup.retrofit2:converter-gson:2.6.0'
    implementation 'com.squareup.retrofit2:retrofit:2.6.0'


    //Recycler View
    implementation 'androidx.recyclerview:recyclerview:1.0.0'
    implementation 'androidx.cardview:cardview:1.0.0'

    // glide for image
    implementation "com.github.bumptech.glide:glide:4.9.0"
    annotationProcessor "com.github.bumptech.glide:compiler:4.9.0"

    // lifecycle
    implementation "android.arch.lifecycle:extensions:1.1.1"
    annotationProcessor "android.arch.lifecycle:compiler:1.1.1"
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [MVVM]() - The Android Design Pattern used
* [RETROFIT](https://square.github.io/retrofit/) - REST Client library (Helper Library) used
* [GRADLE](https://developer.android.com/studio/build) - Dependency Management
* [GLIDE](https://github.com/bumptech/glide) - Used image loading framework for Android

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.


## Authors 
* **Ankit Chaturvedi** - *Initial work*


