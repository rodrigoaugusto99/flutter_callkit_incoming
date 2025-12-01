# 🎨 Recursos Adicionais para Personalização

## Criando Drawables Personalizados

### 1. Background Arredondado para Badge

Crie: `android/src/main/res/drawable/rounded_badge.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="#FF5252" />
    <corners android:radius="12dp" />
    <padding
        android:left="8dp"
        android:top="4dp"
        android:right="8dp"
        android:bottom="4dp" />
</shape>
```

### 2. Botão com Gradiente

Crie: `android/src/main/res/drawable/bg_button_gradient.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval">
    <gradient
        android:angle="135"
        android:startColor="#667eea"
        android:endColor="#764ba2"
        android:type="linear" />
</shape>
```

### 3. Borda com Sombra

Crie: `android/src/main/res/drawable/bg_with_shadow.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- Sombra -->
    <item>
        <shape android:shape="rectangle">
            <solid android:color="#50000000" />
            <corners android:radius="8dp" />
        </shape>
    </item>
    <!-- Conteúdo -->
    <item
        android:bottom="4dp"
        android:right="4dp">
        <shape android:shape="rectangle">
            <solid android:color="@android:color/white" />
            <corners android:radius="8dp" />
        </shape>
    </item>
</layer-list>
```

### 4. Botão com Estados (Pressed/Normal)

Crie: `android/src/main/res/drawable/button_stateful.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- Estado pressionado -->
    <item android:state_pressed="true">
        <shape android:shape="oval">
            <solid android:color="#4CAF50" />
        </shape>
    </item>
    <!-- Estado normal -->
    <item>
        <shape android:shape="oval">
            <solid android:color="#66BB6A" />
        </shape>
    </item>
</selector>
```

## 🎨 Criando Cores Personalizadas

Edite: `android/src/main/res/values/colors.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!-- Cores existentes -->
    <color name="action_text">#FFFFFF</color>

    <!-- Novas cores personalizadas -->
    <color name="call_urgent_red">#D32F2F</color>
    <color name="call_work_blue">#1E88E5</color>
    <color name="call_personal_purple">#7B1FA2</color>
    <color name="call_medical_teal">#00897B</color>
    <color name="call_group_indigo">#6A1B9A</color>

    <!-- Cores de texto -->
    <color name="text_primary_white">#FFFFFF</color>
    <color name="text_secondary_white">#E0E0E0</color>
    <color name="text_accent">#FFEB3B</color>

    <!-- Cores de fundo -->
    <color name="background_dark">#212121</color>
    <color name="background_overlay">#80000000</color>

    <!-- Cores de botões -->
    <color name="button_accept">#4CAF50</color>
    <color name="button_decline">#F44336</color>
    <color name="button_secondary">#757575</color>
</resources>
```

## 📐 Definindo Dimensões Personalizadas

Edite: `android/src/main/res/values/dimens.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!-- Dimensões existentes -->
    <dimen name="base_margin">16dp</dimen>
    <dimen name="size_avatar">100dp</dimen>
    <dimen name="size_button">70dp</dimen>

    <!-- Novas dimensões -->
    <dimen name="badge_height">24dp</dimen>
    <dimen name="badge_padding">12dp</dimen>
    <dimen name="icon_small">24dp</dimen>
    <dimen name="icon_medium">48dp</dimen>
    <dimen name="icon_large">72dp</dimen>

    <!-- Tamanhos de texto -->
    <dimen name="text_small">12sp</dimen>
    <dimen name="text_medium">16sp</dimen>
    <dimen name="text_large">24sp</dimen>
    <dimen name="text_title">30sp</dimen>

    <!-- Espaçamentos -->
    <dimen name="spacing_tiny">4dp</dimen>
    <dimen name="spacing_small">8dp</dimen>
    <dimen name="spacing_medium">16dp</dimen>
    <dimen name="spacing_large">24dp</dimen>
    <dimen name="spacing_xlarge">32dp</dimen>

    <!-- Raios de borda -->
    <dimen name="corner_radius_small">4dp</dimen>
    <dimen name="corner_radius_medium">8dp</dimen>
    <dimen name="corner_radius_large">16dp</dimen>
</resources>
```

## 🔤 Strings Personalizadas

Edite: `android/src/main/res/values/strings.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="app_name">Flutter Callkit Incoming</string>

    <!-- Textos dos botões -->
    <string name="text_accept">Accept</string>
    <string name="text_decline">Decline</string>

    <!-- Novos textos personalizados -->
    <string name="text_accept_video">Accept Video</string>
    <string name="text_accept_audio">Accept Audio</string>
    <string name="text_decline_busy">I\'m Busy</string>
    <string name="text_decline_later">Call Later</string>

    <!-- Tipos de chamada -->
    <string name="call_type_video">Video Call</string>
    <string name="call_type_audio">Voice Call</string>
    <string name="call_type_group">Group Call</string>

    <!-- Status -->
    <string name="status_incoming">Incoming Call</string>
    <string name="status_connecting">Connecting…</string>
    <string name="status_urgent">URGENT</string>
    <string name="status_priority">Priority Call</string>
</resources>
```

## 🖼️ Criando Ícones Personalizados (Vector Drawables)

### Ícone de Vídeo Personalizado

Crie: `android/src/main/res/drawable/ic_video_custom.xml`

```xml
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="24dp"
    android:height="24dp"
    android:viewportWidth="24"
    android:viewportHeight="24">
    <path
        android:fillColor="#FFFFFF"
        android:pathData="M17,10.5V7c0,-0.55 -0.45,-1 -1,-1H4c-0.55,0 -1,0.45 -1,1v10c0,0.55 0.45,1 1,1h12c0.55,0 1,-0.45 1,-1v-3.5l4,4v-11l-4,4z"/>
</vector>
```

### Ícone de Grupo

Crie: `android/src/main/res/drawable/ic_group_custom.xml`

```xml
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="24dp"
    android:height="24dp"
    android:viewportWidth="24"
    android:viewportHeight="24">
    <path
        android:fillColor="#FFFFFF"
        android:pathData="M16,11c1.66,0 2.99,-1.34 2.99,-3S17.66,5 16,5c-1.66,0 -3,1.34 -3,3s1.34,3 3,3zM8,11c1.66,0 2.99,-1.34 2.99,-3S9.66,5 8,5C6.34,5 5,6.34 5,8s1.34,3 3,3zM8,13c-2.33,0 -7,1.17 -7,3.5L1,19h14v-2.5c0,-2.33 -4.67,-3.5 -7,-3.5zM16,13c-0.29,0 -0.62,0.02 -0.97,0.05 1.16,0.84 1.97,1.97 1.97,3.45L17,19h6v-2.5c0,-2.33 -4.67,-3.5 -7,-3.5z"/>
</vector>
```

### Ícone de Urgência

Crie: `android/src/main/res/drawable/ic_urgent.xml`

```xml
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="24dp"
    android:height="24dp"
    android:viewportWidth="24"
    android:viewportHeight="24">
    <path
        android:fillColor="#FF5252"
        android:pathData="M1,21h22L12,2 1,21zM13,18h-2v-2h2v2zM13,14h-2v-4h2v4z"/>
</vector>
```

## 🎭 Animações Personalizadas

### Animação de Pulse (Pulso)

Crie: `android/src/main/res/anim/pulse_anim.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <scale
        android:duration="1000"
        android:fromXScale="1.0"
        android:fromYScale="1.0"
        android:pivotX="50%"
        android:pivotY="50%"
        android:repeatCount="infinite"
        android:repeatMode="reverse"
        android:toXScale="1.1"
        android:toYScale="1.1" />
    <alpha
        android:duration="1000"
        android:fromAlpha="1.0"
        android:repeatCount="infinite"
        android:repeatMode="reverse"
        android:toAlpha="0.7" />
</set>
```

### Animação de Slide Up

Crie: `android/src/main/res/anim/slide_up.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="300"
    android:fillAfter="true">
    <translate
        android:fromYDelta="100%"
        android:toYDelta="0%" />
    <alpha
        android:fromAlpha="0.0"
        android:toAlpha="1.0" />
</set>
```

### Animação de Fade In

Crie: `android/src/main/res/anim/fade_in.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<alpha xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="500"
    android:fromAlpha="0.0"
    android:toAlpha="1.0" />
```

## 🎯 Aplicando Animações no Kotlin

No `CallkitIncomingActivity.kt`, adicione:

```kotlin
private fun animateCustomElement() {
    val pulseAnimation = AnimationUtils.loadAnimation(
        this@CallkitIncomingActivity,
        R.anim.pulse_anim
    )
    tvCustomMessage.animation = pulseAnimation
}

private fun animateSlideUp() {
    val slideUpAnimation = AnimationUtils.loadAnimation(
        this@CallkitIncomingActivity,
        R.anim.slide_up
    )
    llAction.animation = slideUpAnimation
}
```

## 📱 Exemplo de Layout Alternativo Completo

### Layout com Badge, Ícone de Status e Botão Extra

```xml
<com.hiennv.flutter_callkit_incoming.widgets.RippleRelativeLayout
    android:id="@+id/llBackgroundAnimation"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_marginTop="@dimen/base_margin_revert_x5"
    android:layout_weight="1">

    <!-- Badge de Status -->
    <TextView
        android:id="@+id/tvStatusBadge"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="40dp"
        android:background="@drawable/rounded_badge"
        android:text="URGENTE"
        android:textColor="@android:color/white"
        android:textSize="10sp"
        android:textStyle="bold"
        android:visibility="gone" />

    <!-- Logo -->
    <ImageView
        android:id="@+id/ivLogo"
        android:layout_width="match_parent"
        android:layout_height="@dimen/base_margin_x2_5"
        android:layout_above="@id/ivAvatar"
        android:layout_below="@id/tvStatusBadge"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="@dimen/base_margin_x2"
        android:layout_marginBottom="@dimen/base_margin_x5"
        android:adjustViewBounds="true"
        android:scaleType="fitCenter"
        android:src="@drawable/ic_logo"
        android:visibility="invisible" />

    <!-- Avatar com Badge de Tipo -->
    <RelativeLayout
        android:id="@+id/rlAvatarContainer"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true">

        <de.hdodenhof.circleimageview.CircleImageView
            android:id="@+id/ivAvatar"
            android:layout_width="@dimen/size_avatar"
            android:layout_height="@dimen/size_avatar"
            android:src="@drawable/ic_default_avatar"
            android:visibility="invisible"
            app:civ_border_color="#80ffffff"
            app:civ_border_width="1dp" />

        <!-- Badge de tipo de chamada -->
        <ImageView
            android:id="@+id/ivCallTypeBadge"
            android:layout_width="32dp"
            android:layout_height="32dp"
            android:layout_alignBottom="@id/ivAvatar"
            android:layout_alignRight="@id/ivAvatar"
            android:background="@drawable/rounded_badge"
            android:padding="6dp"
            android:src="@drawable/ic_video_custom"
            android:visibility="gone" />
    </RelativeLayout>

    <!-- Informações da Chamada -->
    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/rlAvatarContainer"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="@dimen/base_margin_x2"
        android:gravity="center"
        android:orientation="vertical">

        <TextView
            android:id="@+id/tvNameCaller"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:autoSizeMaxTextSize="@dimen/size_text_name"
            android:autoSizeMinTextSize="12sp"
            android:autoSizeStepGranularity="2sp"
            android:autoSizeTextType="uniform"
            android:ellipsize="end"
            android:maxLines="1"
            android:textColor="@android:color/white"
            android:textSize="@dimen/size_text_name" />

        <TextView
            android:id="@+id/tvNumber"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="4dp"
            android:ellipsize="end"
            android:maxLines="1"
            android:textColor="@color/action_text"
            android:textSize="@dimen/size_text_action" />

        <!-- Mensagem Personalizada -->
        <TextView
            android:id="@+id/tvCustomMessage"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="8dp"
            android:text=""
            android:textColor="@android:color/white"
            android:textSize="14sp"
            android:visibility="gone" />

        <!-- Subtítulo Adicional -->
        <TextView
            android:id="@+id/tvSubtitle"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="4dp"
            android:text=""
            android:textColor="#CCFFFFFF"
            android:textSize="12sp"
            android:visibility="gone" />
    </LinearLayout>

</com.hiennv.flutter_callkit_incoming.widgets.RippleRelativeLayout>
```

## 🔧 Código Kotlin para o Layout Estendido

```kotlin
// Declarações
private lateinit var tvStatusBadge: TextView
private lateinit var ivCallTypeBadge: ImageView
private lateinit var tvSubtitle: TextView
private lateinit var rlAvatarContainer: RelativeLayout

// No initView()
tvStatusBadge = findViewById(R.id.tvStatusBadge)
ivCallTypeBadge = findViewById(R.id.ivCallTypeBadge)
tvSubtitle = findViewById(R.id.tvSubtitle)
rlAvatarContainer = findViewById(R.id.rlAvatarContainer)

// No incomingData()
// Badge de status
val statusBadge = data?.getString("statusBadge", "")
if (!statusBadge.isNullOrEmpty()) {
    tvStatusBadge.text = statusBadge
    tvStatusBadge.visibility = View.VISIBLE
    animatePulse(tvStatusBadge)
}

// Badge de tipo de chamada
val showTypeBadge = data?.getBoolean("showTypeBadge", false) ?: false
if (showTypeBadge) {
    val callType = data?.getInt(CallkitConstants.EXTRA_CALLKIT_TYPE, 0) ?: 0
    if (callType > 0) {
        ivCallTypeBadge.setImageResource(R.drawable.ic_video_custom)
    } else {
        ivCallTypeBadge.setImageResource(R.drawable.ic_accept)
    }
    ivCallTypeBadge.visibility = View.VISIBLE
}

// Subtítulo
val subtitle = data?.getString("subtitle", "")
if (!subtitle.isNullOrEmpty()) {
    tvSubtitle.text = subtitle
    tvSubtitle.visibility = View.VISIBLE
}

// Mensagem personalizada
val customMessage = data?.getString("customMessage", "")
if (!customMessage.isNullOrEmpty()) {
    tvCustomMessage.text = customMessage
    tvCustomMessage.visibility = View.VISIBLE
}

// Função de animação pulse
private fun animatePulse(view: View) {
    val pulseAnimation = AnimationUtils.loadAnimation(
        this@CallkitIncomingActivity,
        R.anim.pulse_anim
    )
    view.animation = pulseAnimation
}
```

## 🎨 Exemplo de Uso Completo no Flutter

```dart
await FlutterCallkitIncoming.showCallkitIncoming(
  CallKitParams(
    id: 'call-${DateTime.now().millisecondsSinceEpoch}',
    nameCaller: 'Dr. Roberto Alves',
    handle: 'Hospital Santa Maria',
    type: 1,
    duration: 40000,

    backgroundColor: '#0277BD',
    textColor: '#FFFFFF',
    logo: 'assets/logo_hospital.png',
    isShowLogo: true,
    avatar: 'https://i.pravatar.cc/300?img=33',
    isShowCallID: true,

    textAccept: 'Aceitar Consulta',
    textDecline: 'Recusar',

    extra: <String, dynamic>{
      'customMessage': '🏥 Consulta de Telemedicina',
      'subtitle': 'Cardiologia',
      'statusBadge': 'URGENTE',
      'showTypeBadge': true,
      'isUrgent': true,
    },
  ),
);
```

---

**Nota**: Após adicionar novos recursos (drawables, animações, strings), sempre faça um **clean build** do projeto Android:

```bash
cd android
./gradlew clean
cd ..
flutter clean
flutter pub get
flutter run
```
