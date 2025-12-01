# Guia de Personalização da Tela de Chamada (Android)

Este guia explica como personalizar a tela de chamada que aparece quando o celular está bloqueado no Android.

## 📱 Arquivos Principais

### 1. Layout XML

- **Localização**: `android/src/main/res/layout/activity_callkit_incoming.xml`
- **Função**: Define a estrutura visual da tela de chamada

### 2. Activity Kotlin

- **Localização**: `android/src/main/kotlin/com/hiennv/flutter_callkit_incoming/CallkitIncomingActivity.kt`
- **Função**: Controla a lógica e os dados da tela de chamada

## 🎨 Personalizações Disponíveis via Flutter

Você pode personalizar diretamente do Flutter sem modificar o código nativo:

```dart
await FlutterCallkitIncoming.showCallkitIncoming(CallKitParams(
  id: 'call-id',
  nameCaller: 'Nome do Contato',
  handle: '+55 11 99999-9999',

  // ===== PERSONALIZAÇÕES =====

  // Cor de fundo
  backgroundColor: '#0955fa',

  // Imagem de fundo (URL ou asset)
  backgroundUrl: 'assets/images/call_background.png',

  // Avatar do contato
  avatar: 'https://example.com/avatar.png',

  // Logo da empresa/app
  logo: 'assets/images/logo.png',
  isShowLogo: true,

  // Cor do texto
  textColor: '#ffffff',

  // Textos dos botões
  textAccept: 'Aceitar',
  textDecline: 'Recusar',

  // Mostrar ou esconder o número/handle
  isShowCallID: true,

  // Tipo de chamada (0 = áudio, 1 = vídeo)
  type: 0,

  // Duração antes de auto-fechar
  duration: 30000,

  // Headers para carregar imagens (se necessário)
  headers: {'Authorization': 'Bearer token'},

  // ===== NOVA PERSONALIZAÇÃO =====
  // Mensagem personalizada adicional
  extra: {
    'customMessage': 'Chamada de vídeo importante',
  },
));
```

## 🔧 Personalizações Avançadas (Modificando o Layout)

### Elementos Visuais Disponíveis

O layout possui os seguintes elementos principais:

1. **`ivBackground`** - Imagem/cor de fundo
2. **`llBackgroundAnimation`** - Animação de ondas no fundo
3. **`ivLogo`** - Logo da empresa
4. **`ivAvatar`** - Avatar do contato
5. **`tvNameCaller`** - Nome do contato
6. **`tvNumber`** - Número/handle do contato
7. **`tvCustomMessage`** - Mensagem personalizada (novo)
8. **`ivAcceptCall`** - Botão de aceitar
9. **`tvAccept`** - Texto "Aceitar"
10. **`ivDeclineCall`** - Botão de recusar
11. **`tvDecline`** - Texto "Recusar"

### Como Adicionar Novos Elementos

#### 1. Adicione no XML (`activity_callkit_incoming.xml`)

```xml
<!-- Exemplo: Adicionar um badge de status -->
<TextView
    android:id="@+id/tvStatusBadge"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_above="@id/ivAvatar"
    android:layout_centerHorizontal="true"
    android:layout_marginBottom="16dp"
    android:background="@drawable/rounded_badge"
    android:padding="8dp"
    android:text="URGENTE"
    android:textColor="@android:color/white"
    android:textSize="12sp"
    android:visibility="gone" />
```

#### 2. Declare no Kotlin (`CallkitIncomingActivity.kt`)

```kotlin
// No topo da classe (linha ~69)
private lateinit var tvStatusBadge: TextView

// No método initView() (linha ~275)
tvStatusBadge = findViewById(R.id.tvStatusBadge)

// No método incomingData() (linha ~219)
val statusBadge = data?.getString("statusBadge", "")
if (!statusBadge.isNullOrEmpty()) {
    tvStatusBadge.text = statusBadge
    tvStatusBadge.visibility = View.VISIBLE
}
```

#### 3. Use no Flutter

```dart
await FlutterCallkitIncoming.showCallkitIncoming(CallKitParams(
  // ... outros parâmetros
  extra: {
    'statusBadge': 'URGENTE',
    'customMessage': 'Reunião importante',
  },
));
```

## 🎯 Exemplos de Personalização

### Exemplo 1: Adicionar um subtítulo

**XML:**

```xml
<TextView
    android:id="@+id/tvSubtitle"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginTop="8dp"
    android:text=""
    android:textColor="#CCFFFFFF"
    android:textSize="12sp"
    android:visibility="gone" />
```

**Kotlin:**

```kotlin
// Declaração
private lateinit var tvSubtitle: TextView

// initView()
tvSubtitle = findViewById(R.id.tvSubtitle)

// incomingData()
val subtitle = data?.getString("subtitle", "")
if (!subtitle.isNullOrEmpty()) {
    tvSubtitle.text = subtitle
    tvSubtitle.visibility = View.VISIBLE
}
```

**Flutter:**

```dart
extra: {'subtitle': 'Chamada de negócios'},
```

### Exemplo 2: Adicionar botões extras

**XML:**

```xml
<ImageView
    android:id="@+id/ivMuteButton"
    android:layout_width="60dp"
    android:layout_height="60dp"
    android:layout_centerInParent="true"
    android:background="@drawable/bg_button_secondary"
    android:padding="16dp"
    android:src="@drawable/ic_mute"
    android:visibility="visible" />
```

**Kotlin:**

```kotlin
private lateinit var ivMuteButton: ImageView

// initView()
ivMuteButton = findViewById(R.id.ivMuteButton)
ivMuteButton.setOnClickListener {
    // Lógica do botão mute
    onMuteClick()
}

private fun onMuteClick() {
    // Implementar lógica de mute
}
```

### Exemplo 3: Mudar cores dinamicamente

**Kotlin no `incomingData()`:**

```kotlin
// Personalizar cores dos botões baseado no tipo de chamada
val isUrgent = data?.getBoolean("isUrgent", false) ?: false
if (isUrgent) {
    ivAcceptCall.setBackgroundResource(R.drawable.bg_button_urgent)
    tvNameCaller.setTextColor(Color.parseColor("#FF5252"))
}
```

**Flutter:**

```dart
extra: {'isUrgent': true},
```

## 📐 Modificar Tamanhos e Espaçamentos

Os tamanhos são definidos em `android/src/main/res/values/dimens.xml`:

```xml
<dimen name="size_avatar">100dp</dimen>
<dimen name="size_button">70dp</dimen>
<dimen name="size_text_name">30sp</dimen>
```

Você pode criar seu próprio arquivo de dimensões ou modificar o existente.

## 🎨 Modificar Cores

As cores são definidas em `android/src/main/res/values/colors.xml`:

```xml
<color name="action_text">#FFFFFF</color>
```

## 🖼️ Modificar Ícones

Os ícones dos botões estão em:

- `android/src/main/res/drawable/ic_accept.xml` - Ícone de aceitar
- `android/src/main/res/drawable/ic_decline.xml` - Ícone de recusar
- `android/src/main/res/drawable/bg_button_accept.xml` - Background do botão aceitar
- `android/src/main/res/drawable/bg_button_decline.xml` - Background do botão recusar

## 📱 Layout para Tablets (Landscape)

Existe um layout alternativo para tablets em modo paisagem:

- `android/src/main/res/layout-w600dp-land/activity_callkit_incoming.xml`

Aplique as mesmas modificações neste arquivo para manter consistência.

## ⚠️ Observações Importantes

1. **Rebuild Necessário**: Após modificar arquivos nativos, você precisa fazer rebuild do app
2. **IDs Únicos**: Sempre use IDs únicos para novos elementos
3. **Visibilidade**: Use `visibility="gone"` para elementos opcionais
4. **Cores**: Use `Color.parseColor()` para cores hexadecimais no Kotlin
5. **Performance**: Evite adicionar muitos elementos pesados (imagens grandes, animações complexas)
6. **Compatibilidade**: Teste em diferentes versões do Android (principalmente API 21+)

## 🔍 Debugging

Para ver os dados sendo recebidos, adicione logs no método `incomingData()`:

```kotlin
Log.d("CallkitIncoming", "Nome: ${data?.getString(CallkitConstants.EXTRA_CALLKIT_NAME_CALLER)}")
Log.d("CallkitIncoming", "Custom: ${data?.getString("customMessage")}")
```

## 📚 Recursos Adicionais

- Animações: Use `AnimationUtils.loadAnimation()` para adicionar animações
- Imagens: Use a classe `ImageLoaderProvider` já presente no projeto
- Temas: Modifique `android/src/main/res/values/themes.xml` para temas globais

## 🚀 Próximos Passos

1. Modifique o layout XML conforme necessário
2. Adicione as referências no Kotlin
3. Passe os dados pelo Flutter usando o campo `extra`
4. Teste com o celular bloqueado
5. Ajuste cores, tamanhos e espaçamentos

---

**Dica**: Comece com modificações simples (textos, cores) antes de adicionar elementos complexos.
