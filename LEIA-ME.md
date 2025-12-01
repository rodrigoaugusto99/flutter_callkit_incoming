# 📱 Como Personalizar a Tela de Chamada

## 🎯 O que você quer fazer?

### 1️⃣ Usar a personalização básica (JÁ PRONTA!)

Cole este código no seu app Flutter:

```dart
await FlutterCallkitIncoming.showCallkitIncoming(
  CallKitParams(
    id: 'chamada-123',
    nameCaller: 'João Silva',
    handle: '+55 11 98765-4321',

    // Mude estas cores:
    backgroundColor: '#1E88E5',  // Azul
    textColor: '#FFFFFF',        // Branco

    // Adicione uma foto:
    avatar: 'https://i.pravatar.cc/300',

    // ⭐ NOVO - Adicione uma mensagem:
    extra: <String, dynamic>{
      'customMessage': 'Chamada de trabalho',
    },
  ),
);
```

**Pronto!** Quando o celular estiver **bloqueado**, vai aparecer assim:

```
┌─────────────────────────┐
│     [Fundo Azul]        │
│                         │
│      👤 Avatar          │
│                         │
│    João Silva           │
│  +55 11 98765-4321     │
│  📞 Chamada de trabalho │ ← NOVO!
│                         │
│   ❌          ✅        │
└─────────────────────────┘
```

### 2️⃣ Ver exemplos prontos

Abra o arquivo [exemplo_personalizacao.dart](exemplo_personalizacao.dart) e copie o exemplo que preferir:

- 📞 Chamada básica
- 🔴 Chamada urgente
- 🏥 Telemedicina
- 💼 Trabalho
- 🍔 Delivery
- 👥 Grupo
- 🌙 Modo noturno

### 3️⃣ Mudar cores

Use estas cores prontas:

```dart
// Urgente (Vermelho)
backgroundColor: '#D32F2F'

// Trabalho (Azul)
backgroundColor: '#1E88E5'

// Saúde (Verde)
backgroundColor: '#00897B'

// Pessoal (Roxo)
backgroundColor: '#7B1FA2'

// Noturno (Cinza escuro)
backgroundColor: '#212121'
```

### 4️⃣ Adicionar mais informações

Além da `customMessage`, você pode adicionar um **subtítulo**:

**Passo 1**: Adicione no XML (`activity_callkit_incoming.xml`, linha ~93):

```xml
<TextView
    android:id="@+id/tvSubtitle"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginTop="8dp"
    android:textColor="#CCFFFFFF"
    android:textSize="12sp"
    android:visibility="gone" />
```

**Passo 2**: Adicione no Kotlin (`CallkitIncomingActivity.kt`):

```kotlin
// Linha ~72 (declarações)
private lateinit var tvSubtitle: TextView

// Linha ~278 (initView)
tvSubtitle = findViewById(R.id.tvSubtitle)

// Linha ~235 (incomingData)
val subtitle = data?.getString("subtitle", "")
if (!subtitle.isNullOrEmpty()) {
    tvSubtitle.text = subtitle
    tvSubtitle.visibility = View.VISIBLE
}
```

**Passo 3**: Use no Flutter:

```dart
extra: {
  'customMessage': '🏥 Consulta médica',
  'subtitle': 'Dr. Roberto - Cardiologia',
},
```

**Passo 4**: Rebuild:

```bash
flutter clean
flutter pub get
flutter run
```

### 5️⃣ Adicionar um badge de status

Exemplo: badge "URGENTE"

**XML** (dentro do `RippleRelativeLayout`):

```xml
<TextView
    android:id="@+id/tvStatusBadge"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_centerHorizontal="true"
    android:layout_marginTop="40dp"
    android:background="#FF5252"
    android:padding="8dp"
    android:textColor="@android:color/white"
    android:textSize="10sp"
    android:textStyle="bold"
    android:visibility="gone" />
```

**Kotlin**:

```kotlin
// Declaração
private lateinit var tvStatusBadge: TextView

// initView
tvStatusBadge = findViewById(R.id.tvStatusBadge)

// incomingData
val statusBadge = data?.getString("statusBadge", "")
if (!statusBadge.isNullOrEmpty()) {
    tvStatusBadge.text = statusBadge
    tvStatusBadge.visibility = View.VISIBLE
}
```

**Flutter**:

```dart
extra: {
  'statusBadge': 'URGENTE',
  'customMessage': 'Reunião de emergência',
},
```

---

## 📚 Documentação Completa

| Arquivo                                                          | Para que serve                                 |
| ---------------------------------------------------------------- | ---------------------------------------------- |
| [INDICE.md](INDICE.md)                                           | 📖 Índice geral - encontre qualquer informação |
| [GUIA_RAPIDO.md](GUIA_RAPIDO.md)                                 | ⚡ Tutorial rápido e prático                   |
| [PERSONALIZACAO_TELA_CHAMADA.md](PERSONALIZACAO_TELA_CHAMADA.md) | 📋 Guia técnico completo                       |
| [MAPA_VISUAL_TELA.md](MAPA_VISUAL_TELA.md)                       | 🗺️ Estrutura visual da tela                    |
| [RECURSOS_ADICIONAIS.md](RECURSOS_ADICIONAIS.md)                 | 🔧 Customizações avançadas                     |
| [ANTES_E_DEPOIS.md](ANTES_E_DEPOIS.md)                           | 👀 Exemplos visuais                            |
| [DICAS_DESIGN.md](DICAS_DESIGN.md)                               | 🎨 Boas práticas de design                     |
| [exemplo_personalizacao.dart](exemplo_personalizacao.dart)       | 💻 Código pronto para copiar                   |

---

## ❓ Perguntas Frequentes

### "A personalização não aparece"

- ✅ O celular está **bloqueado**? (Precisa estar dormindo/bloqueado)
- ✅ Fez rebuild? (`flutter clean && flutter run`)
- ✅ Passou o `extra` com a chave certa?

### "Deu erro ao compilar"

- ✅ Verificou a sintaxe do XML?
- ✅ Os IDs no Kotlin são iguais aos do XML?
- ✅ Executou `./gradlew clean` na pasta android?

### "Quero mudar só a cor"

Use apenas o `backgroundColor`, não precisa mexer no código nativo:

```dart
backgroundColor: '#D32F2F',  // Vermelho urgente
```

### "Qual a diferença entre celular bloqueado e desbloqueado?"

- **Desbloqueado**: Aparece notificação push normal (não personalizada)
- **Bloqueado**: Aparece tela full-screen (com suas personalizações) ✨

---

## 🚀 Começando em 5 Minutos

1. **Copie este código**:

```dart
import 'package:flutter_callkit_incoming/flutter_callkit_incoming.dart';

Future<void> testarChamada() async {
  await FlutterCallkitIncoming.showCallkitIncoming(
    CallKitParams(
      id: 'teste-${DateTime.now().millisecondsSinceEpoch}',
      nameCaller: 'TESTE',
      handle: 'Personalizacao',
      backgroundColor: '#FF5733',
      textColor: '#FFFFFF',
      extra: <String, dynamic>{
        'customMessage': '✅ FUNCIONOU!',
      },
    ),
  );
}
```

2. **Bloqueie a tela** do celular

3. **Execute** o código

4. **Veja** a tela personalizada aparecer!

---

## 🎨 Cores por Tipo de Chamada

```dart
// 🔴 Urgente
backgroundColor: '#D32F2F'
extra: {'customMessage': '🔴 URGENTE'}

// 💼 Trabalho
backgroundColor: '#1E88E5'
extra: {'customMessage': '💼 Reunião'}

// 🏥 Médica
backgroundColor: '#00897B'
extra: {'customMessage': '🏥 Consulta'}

// 💜 Pessoal
backgroundColor: '#7B1FA2'
extra: {'customMessage': '💜 Família'}

// 🍔 Delivery
backgroundColor: '#FF6F00'
extra: {'customMessage': '🍔 Pedido chegando'}

// 🌙 Noturno
backgroundColor: '#212121'
extra: {'customMessage': '🌙 Modo silencioso'}
```

---

## 📱 Arquivos Modificados

Caso você queira adicionar mais personalizações:

```
android/src/main/
├── res/layout/
│   └── activity_callkit_incoming.xml    ← Layout da tela
└── kotlin/.../
    └── CallkitIncomingActivity.kt       ← Lógica da tela
```

**O que já foi modificado**:

- ✅ Adicionado campo `tvCustomMessage` no XML
- ✅ Adicionado suporte no Kotlin
- ✅ Funcional e pronto para usar!

---

## 💡 Dicas Rápidas

1. **Sempre teste com celular bloqueado**
2. **Use cores com bom contraste** (texto branco em fundo escuro)
3. **Mensagens curtas** (máximo 40 caracteres)
4. **Emojis funcionam** mas use com moderação (1-2 por mensagem)
5. **Rebuild sempre** após modificar arquivos nativos

---

## 🎯 Próximos Passos

1. ✅ Testar o exemplo básico
2. ✅ Mudar cores para seu caso de uso
3. ✅ Adicionar avatar/logo
4. ✅ Personalizar mensagens
5. ⚡ (Opcional) Adicionar elementos extras

---

## 📖 Quer Mais?

- **Iniciante**: Leia [GUIA_RAPIDO.md](GUIA_RAPIDO.md)
- **Intermediário**: Leia [PERSONALIZACAO_TELA_CHAMADA.md](PERSONALIZACAO_TELA_CHAMADA.md)
- **Avançado**: Leia [RECURSOS_ADICIONAIS.md](RECURSOS_ADICIONAIS.md)
- **Designer**: Leia [DICAS_DESIGN.md](DICAS_DESIGN.md)
- **Ver tudo**: Leia [INDICE.md](INDICE.md)

---

**🎉 Pronto! Agora você pode personalizar a tela de chamada do seu app!**

**Dúvidas?** Consulte o [INDICE.md](INDICE.md) para encontrar informações específicas.
