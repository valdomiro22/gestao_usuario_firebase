package com.santos.valdomiro.gestaousuariofirebaseauth.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Provides
import javax.inject.Singleton

class FirebaseModule {

    @Provides
    @Singleton
    fun providerFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun providerFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}