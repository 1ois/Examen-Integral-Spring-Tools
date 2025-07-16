import { BrowserRouter,Route,Routes } from 'react-router-dom'
import MainHeader from './common/MainHeader'
import ProductView from './pages/ProductView'
import Presentation from './home/Presentation'
import Cart from './pages/Cart'
import Footer from './home/Footer'

// ...existing code...


function App() {

  return (
    <>
      <BrowserRouter>
        <MainHeader />
        <Routes>
          <Route path="/producto" element={<ProductView />} />
          <Route path="/presentacion" element={<Presentation />} />
          <Route path="/carrito" element={<Cart />} />
        </Routes>
        <Footer />
      </BrowserRouter>
    </>
  )
}

export default App
