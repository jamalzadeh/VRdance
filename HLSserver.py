import aiohttp
from aiohttp import web

async def handle_upload(request):
    data = await request.content.read()
    # Save the received chunk (.ts file or playlist) to disk
    # ....

    return web.Response(text="Chunk received")

app = web.Application()
app.add_routes([web.post('/upload', handle_upload)])

if __name__ == '__main__':
    web.run_app(app, port=8000)