import 'package:flutter_callkit_incoming/flutter_callkit_incoming.dart';

/// Exemplo de como usar a nova tela de delivery personalizada
/// 
/// IMPORTANTE: Se você enviar pickupAddress ou deliveryAddress no extra,
/// a tela de delivery será exibida automaticamente!

Future<void> mostrarChamadaDelivery() async {
  await FlutterCallkitIncoming.showCallkitIncoming(
    CallKitParams(
      id: DateTime.now().millisecondsSinceEpoch.toString(),
      nameCaller: 'Nova Entrega',
      appName: 'Delivery App',
      handle: 'Pedido #1234',
      type: 0,
      duration: 30000,
      extra: <String, dynamic>{
        // Endereços (obrigatório para ativar a tela de delivery)
        'pickupAddress': 'Restaurante Sabor - Rua das Flores, 123',
        'deliveryAddress': 'Cliente João - Av. Principal, 456, Apto 301',
        'estimatedTime': '25 minutos',
        
        // Textos dos botões (opcional)
        'acceptText': 'Aceitar Corrida',
        'declineText': 'Recusar',
      },
      android: AndroidParams(
        backgroundColor: '#0955fa',
        textColor: '#ffffff',
      ),
    ),
  );
}

/// Exemplo com textos personalizados nos botões
Future<void> exemploComBotoesPersonalizados() async {
  await FlutterCallkitIncoming.showCallkitIncoming(
    CallKitParams(
      id: 'corrida-${DateTime.now().millisecondsSinceEpoch}',
      nameCaller: 'Novo Pedido',
      appName: 'Uber Eats',
      handle: '#5678',
      type: 0,
      duration: 30000,
      extra: <String, dynamic>{
        'pickupAddress': 'McDonald\'s - Shopping Center',
        'deliveryAddress': 'Maria Silva - Rua B, 789',
        'estimatedTime': '15-20 min',
        'acceptText': 'Aceitar Entrega',
        'declineText': 'Não Quero',
      },
    ),
  );
}

/// Exemplo sem dados de delivery (usa a tela antiga)
Future<void> exemploSemDelivery() async {
  await FlutterCallkitIncoming.showCallkitIncoming(
    CallKitParams(
      id: 'call-${DateTime.now().millisecondsSinceEpoch}',
      nameCaller: 'João Silva',
      handle: '11 99999-9999',
      type: 0,
      duration: 30000,
      // Sem extra com pickupAddress/deliveryAddress = usa tela antiga
      extra: <String, dynamic>{
        'userId': '123',
      },
    ),
  );
}

/// Exemplo completo com todos os parâmetros
Future<void> exemploCompleto() async {
  await FlutterCallkitIncoming.showCallkitIncoming(
    CallKitParams(
      id: DateTime.now().millisecondsSinceEpoch.toString(),
      nameCaller: 'Nova Corrida Disponível',
      appName: 'Meu App de Delivery',
      avatar: 'https://via.placeholder.com/150',
      handle: 'Corrida #9876',
      type: 0,
      duration: 30000,
      textAccept: 'Aceitar',
      textDecline: 'Rejeitar',
      extra: <String, dynamic>{
        // Dados obrigatórios para tela de delivery
        'pickupAddress': 'Ponto A - Rua das Palmeiras, 100',
        'deliveryAddress': 'Ponto B - Av. Paulista, 1000',
        'estimatedTime': '30 min',
        
        // Textos personalizados (opcional)
        'acceptText': 'ACEITAR AGORA',
        'declineText': 'RECUSAR',
        
        // Outros dados extras que você quiser
        'valorCorrida': '25.00',
        'distancia': '5.2 km',
      },
      android: AndroidParams(
        isCustomNotification: true,
        backgroundColor: '#0955fa',
        textColor: '#ffffff',
      ),
    ),
  );
}

/// Como funciona:
/// 
/// 1. Se você enviar 'pickupAddress' OU 'deliveryAddress' no extra,
///    a nova tela de delivery será exibida
/// 
/// 2. Se NÃO enviar esses campos, a tela antiga será usada
/// 
/// 3. Os campos que podem ser personalizados:
///    - pickupAddress: Endereço de coleta
///    - deliveryAddress: Endereço de entrega
///    - estimatedTime: Tempo estimado
///    - acceptText: Texto do botão aceitar (padrão: "Aceitar")
///    - declineText: Texto do botão rejeitar (padrão: "Rejeitar")
/// 
/// 4. O nome do app vem do campo 'appName' do CallKitParams

