import subprocess

def run_db_drop():
    container_name = "SistemaPrincipal"
    user = "root"
    password = "123456"
    
    databases = [
        "ods01", "ods02", "ods03", "ods04", "ods05", "ods06", "ods07", "ods08", "ods09",
        "ods10", "ods11", "ods12", "ods13", "ods14", "ods15", "ods16", "ods17",
        "ods_master", "ods_login"
    ]
    
    print("="*60)
    print(f"⚠️  PELIGRO: Se van a ELIMINAR {len(databases)} bases de datos")
    print(f"Contenedor: {container_name}")
    print("="*60)
    
    confirm = input("¿Estás seguro de que deseas borrar TODO? (s/n): ")
    if confirm.lower() != 's':
        print("Operación cancelada.")
        return

    try:
        # Generar el comando SQL de DROP
        drop_commands = " ".join([f"DROP DATABASE IF EXISTS {db};" for db in databases])
        
        print("⚙️ Eliminando bases de datos en Docker...")
        
        # Ejecutar directamente en mariadb vía docker exec
        command = f'docker exec -i {container_name} mariadb -u {user} -p{password} -e "{drop_commands}"'
        
        process = subprocess.run(
            command, 
            shell=True, 
            capture_output=True, 
            text=True,
            encoding='utf-8'
        )
        
        if process.returncode == 0:
            print("\n✅ Todas las bases de datos han sido eliminadas correctamente.")
        else:
            print("\n❌ ERROR AL ELIMINAR BASES DE DATOS")
            print("-" * 30)
            print(process.stderr)

    except Exception as e:
        print(f"\n❌ Error de Docker: {e}")

if __name__ == "__main__":
    run_db_drop()
