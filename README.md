# Circuit Designer

تطبيق Android لتصميم الدوائر الإلكترونية ومحاكاتها باستخدام Kotlin وJetpack Compose.

## المميزات
- إضافة مكونات كهربائية إلى لوحة الرسم
- تصميم دوائر بسيطة على الشاشات اللوحية والهواتف
- محاكاة أساسية للجهد والتيار
- قائمة مكونات جاهزة: مقاومة، مصدر جهد، مكثف، LED، مفتاح، أرض، سلك
- واجهة حديثة وجاهزة للتوسعة

## متطلبات التشغيل
- Android Studio Iguana أو أحدث
- JDK 17
- SDK Android 34

## التشغيل
1. افتح المشروع في Android Studio.
2. انتظر حتى يتم تنزيل Gradle Dependencies.
3. اختر جهاز محاكاة أو جهاز فعلي.
4. اضغط Run.

## هيكل المشروع
```text
app/
  src/main/java/com/example/circuitdesigner/
    MainActivity.kt
    CircuitApp.kt
    model/
      CircuitModels.kt
    simulation/
      CircuitEngine.kt
    ui/
      components/
        ComponentPalette.kt
        CircuitCanvas.kt
      screens/
        CircuitScreen.kt
    viewmodel/
      CircuitViewModel.kt
```

## ملاحظات
هذا المشروع هو نسخة MVP عملية ومفيدة جدًا كقاعدة لإنشاء تطبيق تصميم دوائر إلكترونية متكامل. يمكن تطويره لاحقًا بإضافة:
- توصيل الأسلاك من نقطة إلى أخرى
- سحب المكونات وتحريرها
- محاكاة DC و AC
- رسم الموجات
- حفظ المشاريع
- استيراد وتصدير ملفات الدوائر
