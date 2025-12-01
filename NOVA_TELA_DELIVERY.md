# 🚚 Nova Tela de Delivery Personalizada

## O que foi criado?

Uma tela completamente nova e limpa para chamadas de delivery, que é ativada automaticamente quando você envia os dados de endereço.

## 🎨 Layout da Tela

```
┌─────────────────────────┐
│                         │
│      [Nome do App]      │  ← Nome do seu app
│                         │
│         (espaço)        │
│                         │
│  ┌───────────────────┐  │
│  │ COLETA            │  │  ← Endereço de coleta
│  │ Restaurante XYZ   │  │
│  │                   │  │
│  │ ENTREGA           │  │  ← Endereço de entrega
│  │ Cliente - Av X    │  │
│  │                   │  │
│  │ TEMPO ESTIMADO    │  │  ← Tempo estimado
│  │ 25 minutos        │  │
│  └───────────────────┘  │
│                         │
│  [Rejeitar] [Aceitar]   │  ← Botões branco e amarelo
│                         │
└─────────────────────────┘
```

## 🔄 Como Funciona?

### Automático!

O sistema detecta automaticamente se deve usar a nova tela ou a antiga:

- **Tem `pickupAddress` ou `deliveryAddress`?** → Usa a **NOVA TELA** 🚚
- **Não tem?** → Usa a **tela antiga** 📞

## 📝 Como Usar

### Exemplo Básico

```dart
await FlutterCallkitIncoming.showCallkitIncoming(
  CallKitParams(
    id: 'delivery-123',
    nameCaller: 'Nova Entrega',
    appName: 'Meu App',  // ← Aparece no topo da tela
    handle: 'Pedido #1234',
    type: 0,
    duration: 30000,
    extra: <String, dynamic>{
      'pickupAddress': 'Restaurante ABC - Rua X, 123',
      'deliveryAddress': 'Cliente - Av. Y, 456',
      'estimatedTime': '25 minutos',
    },
  ),
);
```

### Personalizando os Botões

```dart
extra: <String, dynamic>{
  'pickupAddress': 'Restaurante ABC',
  'deliveryAddress': 'Cliente XYZ',
  'estimatedTime': '20 min',
  'acceptText': 'PEGAR CORRIDA',    // ← Texto do botão aceitar
  'declineText': 'NÃO QUERO',        // ← Texto do botão rejeitar
},
```

## 📋 Parâmetros Disponíveis

| Campo | Tipo | Obrigatório | Padrão | Descrição |
|-------|------|-------------|--------|-----------|
| `pickupAddress` | String | Não* | - | Endereço de coleta |
| `deliveryAddress` | String | Não* | - | Endereço de entrega |
| `estimatedTime` | String | Não | "Não informado" | Tempo estimado |
| `acceptText` | String | Não | "Aceitar" | Texto do botão aceitar |
| `declineText` | String | Não | "Rejeitar" | Texto do botão rejeitar |

\* *Pelo menos um dos dois (pickupAddress ou deliveryAddress) deve estar presente para ativar a nova tela*

## 🎯 Exemplos de Uso

### 1. Delivery de Comida

```dart
extra: <String, dynamic>{
  'pickupAddress': 'McDonald\'s - Shopping Center',
  'deliveryAddress': 'Maria Silva - Rua B, 789, Apto 45',
  'estimatedTime': '15-20 min',
  'acceptText': 'Aceitar Entrega',
  'declineText': 'Recusar',
},
```

### 2. App de Transporte

```dart
extra: <String, dynamic>{
  'pickupAddress': 'Shopping Iguatemi - Entrada Principal',
  'deliveryAddress': 'Aeroporto Internacional - Terminal 2',
  'estimatedTime': '35 minutos',
  'acceptText': 'Aceitar Corrida',
  'declineText': 'Rejeitar',
},
```

### 3. Entrega de Encomendas

```dart
extra: <String, dynamic>{
  'pickupAddress': 'Centro de Distribuição - Zona Norte',
  'deliveryAddress': 'Residência - Av. Paulista, 1000',
  'estimatedTime': '1 hora',
  'acceptText': 'Pegar Pacote',
  'declineText': 'Não Posso',
},
```

## 🔧 Arquivos Criados

1. **DeliveryCallActivity.kt** - A Activity principal
2. **activity_delivery_call.xml** - O layout da tela
3. Modificações em:
   - `AndroidManifest.xml` - Registro da nova Activity
   - `CallkitConstants.kt` - Nova constante ACTION_DELIVERY_CALL
   - `CallkitNotificationManager.kt` - Lógica de detecção automática

## ✨ Vantagens

- ✅ **Ativação Automática** - Detecta sozinho quando usar
- ✅ **Design Limpo** - Interface focada em delivery
- ✅ **Totalmente Personalizável** - Textos customizáveis
- ✅ **Compatível** - Não quebra código existente
- ✅ **Tela de Bloqueio** - Funciona perfeitamente bloqueado

## 🚀 Testando

1. Use o código de exemplo
2. Envie com `pickupAddress` no extra
3. A nova tela aparecerá automaticamente
4. Para voltar à tela antiga, remova os campos de endereço

## 📱 Visual

- **Fundo**: Azul (#0955fa)
- **Card de Dados**: Branco com informações organizadas
- **Botão Rejeitar**: Branco com texto preto
- **Botão Aceitar**: Amarelo (#FFC107) com texto preto
- **Textos**: Labels em cinza, dados em preto/branco

## ⚠️ Importante

- O campo `appName` no `CallKitParams` define o nome que aparece no topo
- Funciona perfeitamente na tela de bloqueio
- Os botões mantêm a mesma funcionalidade (accept/decline)
- Você pode misturar: algumas chamadas com a nova tela, outras com a antiga

Pronto para usar! 🎉

