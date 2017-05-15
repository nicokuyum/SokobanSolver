function result = fowardExponencial(P,E,beta)
  capas = length(P(:,1));
  entrada = E;
  for i = 1:capas %Por los pesos de cada capa
      aux = P{i,1}; %Matriz de pesos entre las capas
      nro_salidas = length(aux(:,1));
      salida = [1:nro_salidas];
      for j = 1:nro_salidas % Por los pesos de entrada de cada neurona
        suma = dot(aux(j,:),entrada);
        salida(j) = 1 / (1 + exp(-2*beta*suma));     
      end     
      entrada = [-1,salida];
  end
    result = salida;
end


