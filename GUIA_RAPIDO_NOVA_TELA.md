# ⚡ Guia Rápido - Nova Tela de Delivery

## 🎯 Em 30 Segundos

### O Que Mudou?

Criei uma **tela nova** que aparece automaticamente quando você envia dados de delivery.

### Como Ativar?

Adicione isso no `extra`:

```dart
extra: <String, dynamic>{
  'pickupAddress': 'Restaurante ABC',
  'deliveryAddress': 'Cliente XYZ',
  'estimatedTime': '20 min',
}
```

### Pronto! 🎉

É só isso. O resto é automático.

---

## 📝 Código Completo (Copiar e Colar)

```dart
import 'package:flutter_callkit_incoming/flutter_callkit_incoming.dart';

Future<void> mostrarChamada() async {
  await FlutterCallkitIncoming.showCallkitIncoming(
    CallKitParams(
      id: DateTime.now().millisecondsSinceEpoch.toString(),
      nameCaller: 'Nova Entrega',
      appName: 'Meu App',
      handle: 'Pedido #1234',
      type: 0,
      duration: 30000,
      extra: <String, dynamic>{
        'pickupAddress': 'Restaurante - Rua X, 123',
        'deliveryAddress': 'Cliente - Av. Y, 456',
        'estimatedTime': '25 minutos',
      },
    ),
  );
}
```

---

## 🎨 O Que Aparece na Tela?

```
╔═══════════════════╗
║   MEU APP         ║ ← appName
║                   ║
║ ┌───────────────┐ ║
║ │ COLETA        │ ║ ← pickupAddress
║ │ Restaurante X │ ║
║ │               │ ║
║ │ ENTREGA       │ ║ ← deliveryAddress
║ │ Cliente Y     │ ║
║ │               │ ║
║ │ TEMPO ESTIMADO│ ║ ← estimatedTime
║ │ 25 minutos    │ ║
║ └───────────────┘ ║
║                   ║
║ [Rejeitar] [✓]   ║ ← Botões
╚═══════════════════╝
```

---

## 🔧 Personalizar Botões (Opcional)

```dart
extra: <String, dynamic>{
  'pickupAddress': 'Restaurante ABC',
  'deliveryAddress': 'Cliente XYZ',
  'estimatedTime': '20 min',
  'acceptText': 'PEGAR CORRIDA',    // ← Muda o texto
  'declineText': 'NÃO QUERO',        // ← Muda o texto
}
```

---

## ✅ Checklist

- [ ] Adicionar `pickupAddress` no extra
- [ ] Adicionar `deliveryAddress` no extra  
- [ ] Adicionar `estimatedTime` no extra
- [ ] Testar com celular bloqueado
- [ ] Verificar se apareceu a nova tela

---

## 🆘 Problemas?

### A nova tela não aparece?
**Certifique-se** de ter pelo menos `pickupAddress` ou `deliveryAddress` no extra.

### Ainda usa a tela antiga?
**Limpe o app** e instale novamente:
```bash
flutter clean
flutter run
```

### Dados não aparecem?
**Verifique** se os nomes estão corretos:
- `pickupAddress` (não PickupAddress)
- `deliveryAddress` (não DeliveryAddress)
- `estimatedTime` (não EstimatedTime)

---

## 📚 Mais Info?

- **Documentação completa**: `NOVA_TELA_DELIVERY.md`
- **Exemplos variados**: `exemplo_tela_delivery.dart`
- **Resumo técnico**: `RESUMO_FINAL.md`

---

## 🚀 Começar Agora

1. **Copie o código** acima
2. **Cole no seu projeto**
3. **Rode e teste**
4. **Pronto!** ✨

É simples assim! 🎉

