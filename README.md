# 🛍️ Bazaarly - Ecommerce App

> **A modern, feature-rich ecommerce app built with MVVM architecture, Hilt, Firebase, and Room. It allows users to browse products, add them to a cart, and view their cart.**

---


---

## 🚀 Features

- 🌐 Fetches product categories and products from Firebase Firestore.
- 🛒 Add products to a local shopping cart using Room.
- 🛍️ View and manage items in the shopping cart.
- 🧠 MVVM Architecture with `ViewModel` and `Repository`.
- 💉 Hilt for dependency injection.
- 📡 Firebase for remote data storage.
- 🚪 Room for local data storage.
- 🔄 Coroutines for asynchronous operations.
- 📱 Simple and clean UI for a seamless user experience.

---

## 🎨 Tech Stack

- **Language:** Kotlin
- **Architecture:** MVVM
- **Networking:** Firebase Firestore
- **Local Storage:** Room
- **Dependency Injection:** Hilt
- **Asynchronous:** Kotlin Coroutines, LiveData
- **UI:** Android Views, RecyclerView, Glide

---

## 🏗️ Project Structure

```
com.surajvanshsv.ecommerceapp/
├── di/
│   └── AppModule.kt
├── model/
│   ├── Category.kt
│   └── Product.kt
├── repo/
│   └── Repository.kt
├── room/
│   ├── CartDao.kt
│   └── CartDatabase.kt
├── util/
│   ├── CategoryAdapter.kt
│   └── ProductAdapter.kt
├── viewmodel/
│   └── MyViewModel.kt
└── views/
    ├── MainActivity.kt
    ├── CategoryItems.kt
    ├── ProductDetails.kt
    └── CartActivity.kt
```

---

## ⚙️ Installation & Run

1. **Clone this repo:**

```bash
git clone https://github.com/surajpsk12/Bazaarly-Ecommerce-App.git
cd Bazaarly-Ecommerce-App
```

2. **Open in Android Studio.**

3. **Connect to Firebase:**
   - Create a new project in the [Firebase Console](https://console.firebase.google.com/).
   - Add an Android app to your project with the package name `com.surajvanshsv.ecommerceapp`.
   - Download the `google-services.json` file and place it in the `app/` directory.
   - Set up Firestore and add a `categories` collection with documents for each category. Each category document should have a `products` sub-collection with product documents.

4. **Run the app on an emulator or physical device.**

---

## 🧪 Future Enhancements

* ✅ User authentication.
* ✅ Order history.
* ✅ Search functionality.
* ✅ Payment gateway integration.

---

## 🤝 Contribution

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change or improve.

---

## 📜 License

MIT © 2025 [Suraj Kumar](https.github.com/surajpsk12)

📬 **Connect with Me**

*   **Suraj Kumar**
*   **Email**: [sk658139@gmail.com](mailto:sk658139@gmail.com)
*   **LinkedIn**: [linkedin.com/in/surajpsk12](https://www.linkedin.com/in/surajpsk12/)
*   **GitHub**: [github.com/surajpsk12](https://github.com/surajpsk12)
